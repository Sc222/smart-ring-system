"use strict";
// !!! todo add possibility to work with multiple devices and process more than 3 actions
//todo use better nodejs smart things library
let express = require("express");
let bodyParser = require("body-parser");
var request = require("request");
let app = express();
app.use(bodyParser.json({ type: "application/json" }));


let discoveryResponse = require("./discoveryResponse.json");
let refreshResponse = require("./refreshResponse.json");

// Helper functions from ST-schema package to build st-schema responses
const { partnerHelper, CommandResponse } = require("st-schema");
const stPartnerHelper = new partnerHelper({}, {});

//tokens for callbacks
let accessToken = "";
let refreshToken = "";

let buttonStates = {
  pushed: true,
  held: true,
  double: true,
  pushed_2x: true,
  pushed_3x: true,
  pushed_4x: true,
  pushed_5x: true,
  pushed_6x: true,
  down: true,
  down_2x: true,
  down_3x: true,
  down_4x: true,
  down_5x: true,
  down_6x: true,
  down_hold: true,
  up: true,
  up_2x: true,
  up_3x: true,
  up_4x: true,
  up_5x: true,
  up_6x: true,
  up_hold: true
};

/**
 * Handle discovery request interaction type from SmartThings
 */
function discoveryRequest(requestId) {
  discoveryResponse.headers.requestId = requestId;
  console.log(discoveryResponse);
  return discoveryResponse;
}

/**
 * Handle command request interaction type from SmartThings
 */
function commandRequest(requestId, devices) {
  let response = new CommandResponse(requestId);
  devices.map(({ externalDeviceId, deviceCookie, commands }) => {
    const device = response.addDevice(externalDeviceId, deviceCookie);
    stPartnerHelper.mapSTCommandsToState(device, commands);
  });
  console.log("response: %j", response);
  return response;
}

/**
 * Handle state refresh request interaction type from SmartThings
 */
function stateRefreshRequest(requestId, devices) {
  let response = {
    headers: {
      schema: "st-schema",
      version: "1.0",
      interactionType: "stateRefreshResponse",
      requestId: requestId
    },
    deviceState: []
  };
  devices.map(({ externalDeviceId, deviceCookie }) => {
    console.log("externalDeviceId: ", externalDeviceId);
    let deviceResponse = refreshResponse; //[btn_state]
    response.deviceState.push(deviceResponse);
    console.log("deviceResponse: ", deviceResponse);
  });

  console.log(response);
  return response;
}

/**
 *method to get callback credentials
 */
function grantCallbackAccess(headers, callbackAuthentication) {
  console.log("grantCallbackAccess token is:", callbackAuthentication.code);
  console.log(
    "grantCallbackAccess clientId is:",
    callbackAuthentication.clientId
  );

  let requestJson = {
    headers: {
      schema: "st-schema",
      version: "1.0",
      interactionType: "accessTokenRequest",
      requestId: headers.requestId
    },
    //todo regenerate clientSecret
    callbackAuthentication: {
      grantType: "authorization_code",
      code: callbackAuthentication.code,
      clientId: process.env.CLIENT_ID,
      clientSecret: process.env.CLIENT_SECRET
    }
  };

  request.post(
    "https://c2c-eu.smartthings.com/oauth/token",
    { json: requestJson },
    function(error, response, body) {
      if (!error && response.statusCode == 200) {
        //got callback credentials
        console.log("CREDENTIALS REQUEST RESPONSE");
        console.log(body);

        accessToken = body.callbackAuthentication.accessToken;
        refreshToken = body.callbackAuthentication.refreshToken;
        console.log("TOKEN RESPONSE");
        console.log("token:", accessToken);
      }
    }
  );

  console.log({
    headers: {
      schema: "st-schema",
      version: "1.0",
      interactionType: "accessTokenRequest",
      requestId: headers.requestId
    },
    callbackAuthentication: {
      grantType: "authorization_code",
      code: callbackAuthentication.code,
      clientId: process.env.CLIENT_ID,
      clientSecret: process.env.CLIENT_SECRET
    }
  });
  return {};
}

// Renders the homepage
app.get("/", function(req, res) {
  res.writeHead(200, { "Content-Type": "application/json" });
  res.write(JSON.stringify(discoveryResponse));
  res.end();
});

// [START Action]
app.post("/", function(req, res) {
  console.log("Request received: " + JSON.stringify(req.body));

  let response;
  const {
    headers,
    authentication,
    devices,
    callbackAuthentication,
    globalError,
    deviceState
  } = req.body;
  const { interactionType, requestId } = headers;
  console.log("request type: ", interactionType);
  try {
    switch (interactionType) {
      case "discoveryRequest":
        response = discoveryRequest(requestId);
        break;
      case "commandRequest":
        response = commandRequest(requestId, devices);
        break;
      case "stateRefreshRequest":
        response = stateRefreshRequest(requestId, devices);
        break;
      case "grantCallbackAccess":
        response = grantCallbackAccess(requestId, callbackAuthentication);
        break;
      case "integrationDeleted":
        console.log("integration to SmartThings deleted");
        break;
      case "integrationResult":
        console.log("integration result:");
        console.log();
        break;
      default:
        response = "error. not supported interactionType " + interactionType;
        console.log(response);
        break;
    }
  } catch (ex) {
    console.log("failed with ex", ex);
  }

  res.send(response);
});

/**
 *https://smart-ring-webhook.glitch.me/save-state
 *process request from android application and send updates to samsung cloud
 */
app.get("/update", function(req, res) {
  console.log("Mobile app request received: " + JSON.stringify(req.body));
  //todo pass button id req.query.buttonid
  let buttonState = req.query.state;
  if (!buttonState in buttonStates)
    {
      res.writeHead(200, { "Content-Type": "application/json" });
      res.write(JSON.stringify({ updateResult: "wrong button state" }));
      res.end();
      console.log("wrong button state");
      return;
    }
  console.log(buttonState);
  console.log(accessToken);

  let requestJson = {
    headers: {
      schema: "st-schema",
      version: "1.0",
      interactionType: "stateCallback",
      requestId: "abc-123-456"
    },
    authentication: {
      tokenType: "Bearer",
      token: accessToken
    },
    deviceState: [
      {
        externalDeviceId: "smart-ring",
        states: [
          {
            component: "main",
            capability: "st.button",
            attribute: "button",
            value: buttonState,
            timestamp: Date.now()
          },

          {
            component: "main",
            capability: "st.healthCheck",
            attribute: "healthStatus",
            value: "online",
            timestamp: Date.now()
          }
        ]
      }
    ]
  };

  request.post(
    "https://c2c-eu.smartthings.com/device/events",
    { json: requestJson },
    function(error, response, body) {
      if (error) {
        console.log(error);
      }
      if (response.statusCode == 400) {
        console.log("UPDATED BUTTON STATE WITH CODE: " + response.statusCode);
        console.log(body.deviceState);
        console.log(body.deviceState.deviceError);
      } else if (response.statusCode == 401) {
        console.log("UPDATED BUTTON STATE WITH CODE: " + response.statusCode);
        console.log(body);
      }
      if (!error && response.statusCode == 200) {
        //got callback for button update
        console.log("UPDATED BUTTON STATE WITH RESPONSE");
        console.log(body);
      }
      res.writeHead(200, { "Content-Type": "application/json" });
      res.write(JSON.stringify({ updateResult: "completed with code: "+response.statusCode }));
      res.end();
    }
  );
});

if (module === require.main) {
  // [START server]
  let server = app.listen(process.env.PORT || 3000, function() {
    let port = server.address().port;
    console.log("App listening on port %s", port);
  });
  // [END server]
}

module.exports = app;
