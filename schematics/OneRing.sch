EESchema Schematic File Version 4
EELAYER 30 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L Device:Antenna_Chip AE1
U 1 1 5EF389ED
P 6800 3550
F 0 "AE1" H 6980 3722 50  0000 L CNN
F 1 "2.4 GHz" H 6980 3631 50  0000 L CNN
F 2 "RF_Antenna:Johanson_2450AT43F0100" H 6700 3725 50  0001 C CNN
F 3 "https://www.johansontechnology.com/datasheets/2450AT18A100/2450AT18A100.pdf" H 6700 3725 50  0001 C CNN
F 4 "2450AT18A100E" H 6980 3540 50  0000 L CNN "Part Number"
	1    6800 3550
	1    0    0    -1  
$EndComp
$Comp
L Device:L_Small L2
U 1 1 5EF3BFA3
P 7250 3750
F 0 "L2" V 7300 3600 50  0000 C CNN
F 1 "3.9u" V 7200 3750 50  0000 C CNN
F 2 "Inductor_SMD:L_0402_1005Metric" H 7250 3750 50  0001 C CNN
F 3 "~" H 7250 3750 50  0001 C CNN
	1    7250 3750
	0    -1   -1   0   
$EndComp
$Comp
L Device:Battery_Cell BT1
U 1 1 5EF3CAD8
P 4800 2150
F 0 "BT1" H 4918 2246 50  0000 L CNN
F 1 "30 mAh" H 4918 2155 50  0000 L CNN
F 2 "chrns_rf_connector:lipol_10x10" V 4800 2210 50  0001 C CNN
F 3 "~" V 4800 2210 50  0001 C CNN
F 4 "https://aliexpress.ru/item/4000669479179.html" H 4800 2150 50  0001 C CNN "Link"
	1    4800 2150
	1    0    0    -1  
$EndComp
$Comp
L Device:C C12
U 1 1 5EF4364C
P 7000 4000
F 0 "C12" H 7050 4100 50  0000 L CNN
F 1 "0.8p" H 7050 3900 50  0000 L CNN
F 2 "Capacitor_SMD:C_0402_1005Metric" H 7038 3850 50  0001 C CNN
F 3 "~" H 7000 4000 50  0001 C CNN
	1    7000 4000
	1    0    0    -1  
$EndComp
Wire Wire Line
	7000 3850 7000 3750
Wire Wire Line
	7000 3750 7150 3750
Wire Wire Line
	6900 3650 6900 3750
Wire Wire Line
	6900 3750 7000 3750
Connection ~ 7000 3750
Wire Wire Line
	6700 3650 6700 4250
Wire Wire Line
	7000 4150 7000 4250
Text Label 8000 3750 2    50   ~ 0
NRF_ANT
$Comp
L power:GND #PWR030
U 1 1 5EF4C84D
P 7000 4250
F 0 "#PWR030" H 7000 4000 50  0001 C CNN
F 1 "GND" H 7005 4077 50  0000 C CNN
F 2 "" H 7000 4250 50  0001 C CNN
F 3 "" H 7000 4250 50  0001 C CNN
	1    7000 4250
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR028
U 1 1 5EF4CA91
P 6700 4250
F 0 "#PWR028" H 6700 4000 50  0001 C CNN
F 1 "GND" H 6705 4077 50  0000 C CNN
F 2 "" H 6700 4250 50  0001 C CNN
F 3 "" H 6700 4250 50  0001 C CNN
	1    6700 4250
	1    0    0    -1  
$EndComp
$Comp
L Mechanical:Fiducial FID1
U 1 1 5EF50F73
P 6500 7500
F 0 "FID1" H 6585 7546 50  0000 L CNN
F 1 "Fiducial" H 6585 7455 50  0000 L CNN
F 2 "Fiducial:Fiducial_0.5mm_Mask1mm" H 6500 7500 50  0001 C CNN
F 3 "~" H 6500 7500 50  0001 C CNN
	1    6500 7500
	1    0    0    -1  
$EndComp
Wire Wire Line
	5500 3600 5500 3500
Wire Wire Line
	5500 3900 5500 4000
Wire Wire Line
	5500 4000 5000 4000
Wire Wire Line
	5500 3500 5000 3500
Text Label 5000 4000 0    50   ~ 0
NRF_XC_N
Text Label 5000 3500 0    50   ~ 0
NRF_XC_P
$Comp
L Device:C C8
U 1 1 5EF5CC64
P 5750 4000
F 0 "C8" V 5700 4100 50  0000 L CNN
F 1 "10p" V 5800 4100 50  0000 L CNN
F 2 "Capacitor_SMD:C_0402_1005Metric" H 5788 3850 50  0001 C CNN
F 3 "~" H 5750 4000 50  0001 C CNN
F 4 "NP0" V 5900 4000 50  0000 C CNN "Material"
	1    5750 4000
	0    1    1    0   
$EndComp
$Comp
L Device:C C7
U 1 1 5EF5D5BD
P 5750 3500
F 0 "C7" V 5700 3600 50  0000 L CNN
F 1 "10p" V 5800 3600 50  0000 L CNN
F 2 "Capacitor_SMD:C_0402_1005Metric" H 5788 3350 50  0001 C CNN
F 3 "~" H 5750 3500 50  0001 C CNN
F 4 "NP0" V 5600 3500 50  0000 C CNN "Material"
	1    5750 3500
	0    1    1    0   
$EndComp
Wire Wire Line
	5600 4000 5500 4000
Connection ~ 5500 4000
Wire Wire Line
	5600 3500 5500 3500
Connection ~ 5500 3500
Wire Wire Line
	6100 4000 6100 3750
Wire Wire Line
	5900 4000 6100 4000
Wire Wire Line
	5900 3500 6100 3500
Wire Wire Line
	6100 4000 6100 4100
Connection ~ 6100 4000
$Comp
L power:GND #PWR023
U 1 1 5EF60E1C
P 6100 4100
F 0 "#PWR023" H 6100 3850 50  0001 C CNN
F 1 "GND" H 6105 3927 50  0000 C CNN
F 2 "" H 6100 4100 50  0001 C CNN
F 3 "" H 6100 4100 50  0001 C CNN
	1    6100 4100
	1    0    0    -1  
$EndComp
Text Label 9500 5700 0    50   ~ 0
NRF_RESET
Text Label 9500 5800 0    50   ~ 0
NRF_SWDCLK
Text Label 9500 5900 0    50   ~ 0
NRF_SWDIO
$Comp
L power:GND #PWR014
U 1 1 5EF6DCF4
P 4800 2350
F 0 "#PWR014" H 4800 2100 50  0001 C CNN
F 1 "GND" H 4805 2177 50  0000 C CNN
F 2 "" H 4800 2350 50  0001 C CNN
F 3 "" H 4800 2350 50  0001 C CNN
	1    4800 2350
	1    0    0    -1  
$EndComp
$Comp
L power:+BATT #PWR015
U 1 1 5EF6EC2B
P 4900 1900
F 0 "#PWR015" H 4900 1750 50  0001 C CNN
F 1 "+BATT" V 4900 2150 50  0000 C CNN
F 2 "" H 4900 1900 50  0001 C CNN
F 3 "" H 4900 1900 50  0001 C CNN
	1    4900 1900
	0    1    1    0   
$EndComp
$Comp
L Connector:TestPoint TP2
U 1 1 5EF73ECE
P 10500 5700
F 0 "TP2" V 10450 5700 50  0000 C CNN
F 1 "SWDIO" V 10500 5900 50  0000 L CNN
F 2 "TestPoint:TestPoint_Pad_D1.0mm" H 10700 5700 50  0001 C CNN
F 3 "~" H 10700 5700 50  0001 C CNN
	1    10500 5700
	0    1    1    0   
$EndComp
$Comp
L Connector:TestPoint TP3
U 1 1 5EF7509E
P 10500 5800
F 0 "TP3" V 10450 5800 50  0000 C CNN
F 1 "SWDCLK" V 10500 6000 50  0000 L CNN
F 2 "TestPoint:TestPoint_Pad_D1.0mm" H 10700 5800 50  0001 C CNN
F 3 "~" H 10700 5800 50  0001 C CNN
	1    10500 5800
	0    1    1    0   
$EndComp
$Comp
L Connector:TestPoint TP4
U 1 1 5EF7531B
P 10500 5900
F 0 "TP4" V 10450 5900 50  0000 C CNN
F 1 "RESET" V 10500 6100 50  0000 L CNN
F 2 "TestPoint:TestPoint_Pad_D1.0mm" H 10700 5900 50  0001 C CNN
F 3 "~" H 10700 5900 50  0001 C CNN
	1    10500 5900
	0    1    1    0   
$EndComp
Wire Wire Line
	9500 5700 10500 5700
Wire Wire Line
	10500 5800 9500 5800
Wire Wire Line
	9500 5900 10500 5900
Text Label 9500 6000 0    50   ~ 0
NRF_SWO
Wire Wire Line
	9500 6000 10500 6000
$Comp
L Connector:TestPoint TP5
U 1 1 5EF7E23D
P 10500 6000
F 0 "TP5" V 10450 6000 50  0000 C CNN
F 1 "LOGGER" V 10500 6200 50  0000 L CNN
F 2 "TestPoint:TestPoint_Pad_D1.0mm" H 10700 6000 50  0001 C CNN
F 3 "~" H 10700 6000 50  0001 C CNN
	1    10500 6000
	0    1    1    0   
$EndComp
$Comp
L Connector:TestPoint TP6
U 1 1 5EF8161A
P 10500 6100
F 0 "TP6" V 10450 6100 50  0000 C CNN
F 1 "GND" V 10500 6300 50  0000 L CNN
F 2 "TestPoint:TestPoint_Pad_D1.0mm" H 10700 6100 50  0001 C CNN
F 3 "~" H 10700 6100 50  0001 C CNN
	1    10500 6100
	0    1    1    0   
$EndComp
$Comp
L Connector:TestPoint TP1
U 1 1 5EF818EA
P 10500 5600
F 0 "TP1" V 10450 5600 50  0000 C CNN
F 1 "VDD" V 10500 5800 50  0000 L CNN
F 2 "TestPoint:TestPoint_Pad_D1.0mm" H 10700 5600 50  0001 C CNN
F 3 "~" H 10700 5600 50  0001 C CNN
	1    10500 5600
	0    1    1    0   
$EndComp
Wire Wire Line
	10500 5600 10400 5600
Wire Wire Line
	10400 5600 10400 5500
Wire Wire Line
	10500 6100 10400 6100
Wire Wire Line
	10400 6100 10400 6200
$Comp
L power:GND #PWR036
U 1 1 5EF837E6
P 10400 6200
F 0 "#PWR036" H 10400 5950 50  0001 C CNN
F 1 "GND" H 10405 6027 50  0000 C CNN
F 2 "" H 10400 6200 50  0001 C CNN
F 3 "" H 10400 6200 50  0001 C CNN
	1    10400 6200
	1    0    0    -1  
$EndComp
Text Notes 9450 6050 1    50   ~ 0
programmer
$Comp
L Device:LED_ALT D3
U 1 1 5EFB17E0
P 8250 6000
F 0 "D3" H 8250 5900 50  0000 C CNN
F 1 "GREEN" H 8250 6100 50  0000 C CNN
F 2 "LED_SMD:LED_0402_1005Metric" H 8250 6000 50  0001 C CNN
F 3 "~" H 8250 6000 50  0001 C CNN
	1    8250 6000
	-1   0    0    1   
$EndComp
$Comp
L Device:R R4
U 1 1 5EFB42C1
P 7750 6000
F 0 "R4" V 7650 6000 50  0000 C CNN
F 1 "560" V 7750 6000 50  0000 C CNN
F 2 "Resistor_SMD:R_0402_1005Metric" V 7680 6000 50  0001 C CNN
F 3 "~" H 7750 6000 50  0001 C CNN
	1    7750 6000
	0    1    1    0   
$EndComp
Wire Wire Line
	7900 6000 8100 6000
Wire Wire Line
	7600 6000 7500 6000
Wire Wire Line
	7500 6000 7500 5900
$Comp
L power:+3.3V #PWR032
U 1 1 5EFBB626
P 7500 5900
F 0 "#PWR032" H 7500 5750 50  0001 C CNN
F 1 "+3.3V" H 7515 6073 50  0000 C CNN
F 2 "" H 7500 5900 50  0001 C CNN
F 3 "" H 7500 5900 50  0001 C CNN
	1    7500 5900
	1    0    0    -1  
$EndComp
Wire Wire Line
	9000 6000 8400 6000
Text Label 9000 6000 2    50   ~ 0
NRF_LED
$Comp
L chrns_ic:LTC4124 U2
U 1 1 5EFC6AB2
P 4800 1400
F 0 "U2" H 4350 1900 50  0000 C CNN
F 1 "LTC4124" H 5150 1900 50  0000 C CNN
F 2 "chrns_ic:LQFN12" H 4800 1900 50  0001 C CNN
F 3 "https://www.analog.com/media/en/technical-documentation/data-sheets/LTC4124.pdf" H 3500 1400 50  0001 C CNN
	1    4800 1400
	1    0    0    -1  
$EndComp
Wire Wire Line
	5400 1700 5500 1700
Wire Wire Line
	4200 1200 4100 1200
Wire Wire Line
	4100 1200 4100 1400
Wire Wire Line
	4100 1700 4200 1700
Wire Wire Line
	5400 1000 5600 1000
$Comp
L power:GND #PWR019
U 1 1 5EFD6934
P 5600 1000
F 0 "#PWR019" H 5600 750 50  0001 C CNN
F 1 "GND" V 5605 872 50  0000 R CNN
F 2 "" H 5600 1000 50  0001 C CNN
F 3 "" H 5600 1000 50  0001 C CNN
	1    5600 1000
	0    -1   -1   0   
$EndComp
Text Notes 3450 2050 0    50   ~ 0
4.2V (VSEL1 = 1, VSEL2 = 0)\n10 mA (ISEL1 = 0, ISEL2 = 0)\n3.2V disconnect (LBSEL = 1)
NoConn ~ 5400 1500
Wire Wire Line
	4800 1900 4900 1900
Wire Wire Line
	5400 1400 5500 1400
Wire Wire Line
	5400 1200 5500 1200
Wire Wire Line
	5500 1200 5500 1400
Connection ~ 5500 1400
Wire Wire Line
	5500 1400 5600 1400
Wire Wire Line
	5400 1100 5500 1100
Wire Wire Line
	5500 1100 5500 1200
Connection ~ 5500 1200
Wire Wire Line
	3800 1000 3800 900 
$Comp
L power:VDD #PWR035
U 1 1 5F015659
P 10400 5500
F 0 "#PWR035" H 10400 5350 50  0001 C CNN
F 1 "VDD" H 10415 5673 50  0000 C CNN
F 2 "" H 10400 5500 50  0001 C CNN
F 3 "" H 10400 5500 50  0001 C CNN
	1    10400 5500
	1    0    0    -1  
$EndComp
$Comp
L power:VDD #PWR020
U 1 1 5F015918
P 5600 1400
F 0 "#PWR020" H 5600 1250 50  0001 C CNN
F 1 "VDD" H 5615 1573 50  0000 C CNN
F 2 "" H 5600 1400 50  0001 C CNN
F 3 "" H 5600 1400 50  0001 C CNN
	1    5600 1400
	0    1    1    0   
$EndComp
$Comp
L Device:C C1
U 1 1 5F01978C
P 3000 1250
F 0 "C1" H 3050 1350 50  0000 L CNN
F 1 "3.3n" H 3050 1150 50  0000 L CNN
F 2 "Capacitor_SMD:C_0402_1005Metric" H 3038 1100 50  0001 C CNN
F 3 "~" H 3000 1250 50  0001 C CNN
F 4 "X7R" H 3200 1250 50  0000 C CNN "Material"
	1    3000 1250
	1    0    0    -1  
$EndComp
$Comp
L power:VDC #PWR08
U 1 1 5F0297F7
P 3800 900
F 0 "#PWR08" H 3800 800 50  0001 C CNN
F 1 "VDC" H 3815 1073 50  0000 C CNN
F 2 "" H 3800 900 50  0001 C CNN
F 3 "" H 3800 900 50  0001 C CNN
	1    3800 900 
	1    0    0    -1  
$EndComp
$Comp
L Device:R R2
U 1 1 5F039C88
P 7250 900
F 0 "R2" V 7150 900 50  0000 C CNN
F 1 "1M" V 7250 900 50  0000 C CNN
F 2 "Resistor_SMD:R_0402_1005Metric" V 7180 900 50  0001 C CNN
F 3 "~" H 7250 900 50  0001 C CNN
	1    7250 900 
	0    1    1    0   
$EndComp
$Comp
L Device:R R3
U 1 1 5F03BC49
P 7750 900
F 0 "R3" V 7650 900 50  0000 C CNN
F 1 "100k" V 7750 900 50  0000 C CNN
F 2 "Resistor_SMD:R_0402_1005Metric" V 7680 900 50  0001 C CNN
F 3 "~" H 7750 900 50  0001 C CNN
	1    7750 900 
	0    1    1    0   
$EndComp
Wire Wire Line
	7400 900  7500 900 
Wire Wire Line
	7500 900  7500 1150
Connection ~ 7500 900 
Wire Wire Line
	7500 900  7600 900 
Wire Wire Line
	7900 1150 8000 1150
Wire Wire Line
	7900 900  8000 900 
Wire Wire Line
	8000 900  8000 650 
Wire Wire Line
	8000 650  7650 650 
Wire Wire Line
	8000 900  8500 900 
Connection ~ 8000 900 
$Comp
L Device:C C13
U 1 1 5F04C1BA
P 7500 650
F 0 "C13" V 7450 700 50  0000 L CNN
F 1 "0.1u" V 7550 700 50  0000 L CNN
F 2 "Capacitor_SMD:C_0402_1005Metric" H 7538 500 50  0001 C CNN
F 3 "~" H 7500 650 50  0001 C CNN
	1    7500 650 
	0    1    1    0   
$EndComp
Wire Wire Line
	7350 650  6950 650 
Wire Wire Line
	6950 650  6950 900 
Wire Wire Line
	6950 900  7100 900 
Wire Wire Line
	7050 1150 7000 1150
Text Label 8500 900  2    50   ~ 0
NRF_BTN
Text Label 8500 4000 0    50   ~ 0
NRF_BAT_LVL
$Comp
L Power_Management:TPS22929D U3
U 1 1 5F06672C
P 7500 1700
F 0 "U3" H 7250 1450 50  0000 C CNN
F 1 "TPS22929D" V 7850 1650 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-23-6" H 7500 1300 50  0001 C CNN
F 3 "http://www.ti.com/lit/ds/symlink/tps22929d.pdf" H 7400 2150 50  0001 C CNN
	1    7500 1700
	1    0    0    -1  
$EndComp
Wire Wire Line
	7900 1500 8000 1500
Wire Wire Line
	8000 1500 8500 1500
Connection ~ 8000 1500
Wire Wire Line
	7100 1600 7000 1600
Wire Wire Line
	7000 1600 7000 1500
Wire Wire Line
	7000 1500 7100 1500
Wire Wire Line
	7000 1150 7000 1500
Connection ~ 7000 1500
$Comp
L Device:C C11
U 1 1 5F07BDC5
P 6500 1750
F 0 "C11" H 6350 1650 50  0000 L CNN
F 1 "1u" H 6350 1850 50  0000 L CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 6538 1600 50  0001 C CNN
F 3 "~" H 6500 1750 50  0001 C CNN
	1    6500 1750
	-1   0    0    1   
$EndComp
Wire Wire Line
	7500 2000 7500 2100
Wire Wire Line
	7500 2100 7600 2100
Wire Wire Line
	7600 2100 7600 2000
Wire Wire Line
	7500 2100 7500 2200
Connection ~ 7500 2100
$Comp
L power:GND #PWR031
U 1 1 5F082E19
P 7500 2200
F 0 "#PWR031" H 7500 1950 50  0001 C CNN
F 1 "GND" H 7505 2027 50  0000 C CNN
F 2 "" H 7500 2200 50  0001 C CNN
F 3 "" H 7500 2200 50  0001 C CNN
	1    7500 2200
	1    0    0    -1  
$EndComp
Text Label 6750 1800 0    50   ~ 0
NRF_PWR
Wire Wire Line
	6500 1500 6500 1600
Wire Wire Line
	6500 1500 7000 1500
$Comp
L power:GND #PWR027
U 1 1 5F0A4DE2
P 6500 2200
F 0 "#PWR027" H 6500 1950 50  0001 C CNN
F 1 "GND" H 6505 2027 50  0000 C CNN
F 2 "" H 6500 2200 50  0001 C CNN
F 3 "" H 6500 2200 50  0001 C CNN
	1    6500 2200
	1    0    0    -1  
$EndComp
Wire Wire Line
	6500 1900 6500 2200
Wire Wire Line
	6500 1500 6500 1400
Connection ~ 6500 1500
$Comp
L power:VDD #PWR026
U 1 1 5F0AFC9B
P 6500 1400
F 0 "#PWR026" H 6500 1250 50  0001 C CNN
F 1 "VDD" H 6515 1573 50  0000 C CNN
F 2 "" H 6500 1400 50  0001 C CNN
F 3 "" H 6500 1400 50  0001 C CNN
	1    6500 1400
	1    0    0    -1  
$EndComp
Wire Wire Line
	4200 1400 4100 1400
Connection ~ 4100 1400
Wire Wire Line
	4100 1400 4100 1700
Wire Wire Line
	4200 1300 4000 1300
Wire Wire Line
	4000 1300 4000 900 
$Comp
L Device:C C6
U 1 1 5F0DE53C
P 5250 6600
F 0 "C6" H 5300 6700 50  0000 L CNN
F 1 "0.1u" H 5300 6500 50  0000 L CNN
F 2 "Capacitor_SMD:C_0402_1005Metric" H 5288 6450 50  0001 C CNN
F 3 "~" H 5250 6600 50  0001 C CNN
	1    5250 6600
	1    0    0    -1  
$EndComp
$Comp
L Device:C C9
U 1 1 5F0DEC0F
P 5750 6600
F 0 "C9" H 5800 6700 50  0000 L CNN
F 1 "0.1u" H 5800 6500 50  0000 L CNN
F 2 "Capacitor_SMD:C_0402_1005Metric" H 5788 6450 50  0001 C CNN
F 3 "~" H 5750 6600 50  0001 C CNN
	1    5750 6600
	1    0    0    -1  
$EndComp
$Comp
L Device:C C10
U 1 1 5F0DEE4F
P 6250 6600
F 0 "C10" H 6300 6700 50  0000 L CNN
F 1 "4.7u" H 6300 6500 50  0000 L CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 6288 6450 50  0001 C CNN
F 3 "~" H 6250 6600 50  0001 C CNN
	1    6250 6600
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR018
U 1 1 5F0ECEE3
P 5250 6850
F 0 "#PWR018" H 5250 6600 50  0001 C CNN
F 1 "GND" H 5255 6677 50  0000 C CNN
F 2 "" H 5250 6850 50  0001 C CNN
F 3 "" H 5250 6850 50  0001 C CNN
	1    5250 6850
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR022
U 1 1 5F0ED235
P 5750 6850
F 0 "#PWR022" H 5750 6600 50  0001 C CNN
F 1 "GND" H 5755 6677 50  0000 C CNN
F 2 "" H 5750 6850 50  0001 C CNN
F 3 "" H 5750 6850 50  0001 C CNN
	1    5750 6850
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR025
U 1 1 5F0ED513
P 6250 6850
F 0 "#PWR025" H 6250 6600 50  0001 C CNN
F 1 "GND" H 6255 6677 50  0000 C CNN
F 2 "" H 6250 6850 50  0001 C CNN
F 3 "" H 6250 6850 50  0001 C CNN
	1    6250 6850
	1    0    0    -1  
$EndComp
$Comp
L power:+3.3V #PWR017
U 1 1 5F0EDA50
P 5250 6350
F 0 "#PWR017" H 5250 6200 50  0001 C CNN
F 1 "+3.3V" H 5265 6523 50  0000 C CNN
F 2 "" H 5250 6350 50  0001 C CNN
F 3 "" H 5250 6350 50  0001 C CNN
	1    5250 6350
	1    0    0    -1  
$EndComp
$Comp
L power:+3.3V #PWR021
U 1 1 5F0EDE0F
P 5750 6350
F 0 "#PWR021" H 5750 6200 50  0001 C CNN
F 1 "+3.3V" H 5765 6523 50  0000 C CNN
F 2 "" H 5750 6350 50  0001 C CNN
F 3 "" H 5750 6350 50  0001 C CNN
	1    5750 6350
	1    0    0    -1  
$EndComp
$Comp
L power:+3.3V #PWR024
U 1 1 5F0EE26E
P 6250 6350
F 0 "#PWR024" H 6250 6200 50  0001 C CNN
F 1 "+3.3V" H 6265 6523 50  0000 C CNN
F 2 "" H 6250 6350 50  0001 C CNN
F 3 "" H 6250 6350 50  0001 C CNN
	1    6250 6350
	1    0    0    -1  
$EndComp
Wire Wire Line
	5250 6850 5250 6750
Wire Wire Line
	5750 6850 5750 6750
Wire Wire Line
	6250 6850 6250 6750
Wire Wire Line
	6250 6350 6250 6450
Wire Wire Line
	5750 6350 5750 6450
Wire Wire Line
	5250 6350 5250 6450
Text Notes 5200 6550 1    50   ~ 0
VDD 13
Text Notes 5700 6550 1    50   ~ 0
VDD 36
Text Notes 6200 6550 1    50   ~ 0
VDD 48
$Comp
L Device:C C2
U 1 1 5F13DBE8
P 4000 6600
F 0 "C2" H 4050 6700 50  0000 L CNN
F 1 "0.1u" H 4050 6500 50  0000 L CNN
F 2 "Capacitor_SMD:C_0402_1005Metric" H 4038 6450 50  0001 C CNN
F 3 "~" H 4000 6600 50  0001 C CNN
F 4 "X7R" H 4050 6400 50  0000 L CNN "Material"
	1    4000 6600
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR010
U 1 1 5F13F3E3
P 4000 6850
F 0 "#PWR010" H 4000 6600 50  0001 C CNN
F 1 "GND" H 4005 6677 50  0000 C CNN
F 2 "" H 4000 6850 50  0001 C CNN
F 3 "" H 4000 6850 50  0001 C CNN
	1    4000 6850
	1    0    0    -1  
$EndComp
Text Label 4000 6000 3    50   ~ 0
NRF_DEC1
Wire Wire Line
	4000 6000 4000 6450
Wire Wire Line
	4000 6850 4000 6750
$Comp
L Device:C C3
U 1 1 5F1504B2
P 4250 6600
F 0 "C3" H 4300 6700 50  0000 L CNN
F 1 "N.C." H 4300 6500 50  0000 L CNN
F 2 "Capacitor_SMD:C_0402_1005Metric" H 4288 6450 50  0001 C CNN
F 3 "~" H 4250 6600 50  0001 C CNN
F 4 "X7R" H 4300 6400 50  0000 L CNN "Material"
	1    4250 6600
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR011
U 1 1 5F1504BC
P 4250 6850
F 0 "#PWR011" H 4250 6600 50  0001 C CNN
F 1 "GND" H 4255 6677 50  0000 C CNN
F 2 "" H 4250 6850 50  0001 C CNN
F 3 "" H 4250 6850 50  0001 C CNN
	1    4250 6850
	1    0    0    -1  
$EndComp
Text Label 4250 6000 3    50   ~ 0
NRF_DEC2
Wire Wire Line
	4250 6000 4250 6450
Wire Wire Line
	4250 6850 4250 6750
$Comp
L Device:C C4
U 1 1 5F1571E1
P 4500 6600
F 0 "C4" H 4550 6700 50  0000 L CNN
F 1 "0.1u" H 4550 6500 50  0000 L CNN
F 2 "Capacitor_SMD:C_0402_1005Metric" H 4538 6450 50  0001 C CNN
F 3 "~" H 4500 6600 50  0001 C CNN
F 4 "X7R" H 4550 6400 50  0000 L CNN "Material"
	1    4500 6600
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR012
U 1 1 5F1571EB
P 4500 6850
F 0 "#PWR012" H 4500 6600 50  0001 C CNN
F 1 "GND" H 4505 6677 50  0000 C CNN
F 2 "" H 4500 6850 50  0001 C CNN
F 3 "" H 4500 6850 50  0001 C CNN
	1    4500 6850
	1    0    0    -1  
$EndComp
Text Label 4500 6000 3    50   ~ 0
NRF_DEC3
Wire Wire Line
	4500 6000 4500 6450
Wire Wire Line
	4500 6850 4500 6750
$Comp
L Device:C C5
U 1 1 5F164F8D
P 4750 6600
F 0 "C5" H 4800 6700 50  0000 L CNN
F 1 "1u" H 4800 6500 50  0000 L CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 4788 6450 50  0001 C CNN
F 3 "~" H 4750 6600 50  0001 C CNN
F 4 "X7R" H 4800 6400 50  0000 L CNN "Material"
	1    4750 6600
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR013
U 1 1 5F164F97
P 4750 6850
F 0 "#PWR013" H 4750 6600 50  0001 C CNN
F 1 "GND" H 4755 6677 50  0000 C CNN
F 2 "" H 4750 6850 50  0001 C CNN
F 3 "" H 4750 6850 50  0001 C CNN
	1    4750 6850
	1    0    0    -1  
$EndComp
Text Label 4750 6000 3    50   ~ 0
NRF_DEC4
Wire Wire Line
	4750 6000 4750 6450
Wire Wire Line
	4750 6850 4750 6750
Wire Wire Line
	7350 3750 8000 3750
$Comp
L Connector_Generic:Conn_01x02 J1
U 1 1 5F1B6795
P 800 1600
F 0 "J1" H 800 1400 50  0000 C CNN
F 1 "Charger" V 900 1550 50  0000 C CNN
F 2 "chrns_rf_connector:pogo_2x_in" H 800 1600 50  0001 C CNN
F 3 "~" H 800 1600 50  0001 C CNN
	1    800  1600
	-1   0    0    1   
$EndComp
Wire Wire Line
	1100 1600 1100 1700
NoConn ~ 2750 1100
NoConn ~ 2750 1400
Wire Wire Line
	3000 1100 3000 1000
Wire Wire Line
	3000 1400 3000 1500
$Comp
L Connector_Generic:Conn_01x01 J2
U 1 1 5F1E29A8
P 2550 1000
F 0 "J2" H 2550 900 50  0000 C CNN
F 1 "L+" H 2700 1000 50  0000 C CNN
F 2 "Connector_Pin:Pin_D1.0mm_L10.0mm_LooseFit" H 2550 1000 50  0001 C CNN
F 3 "~" H 2550 1000 50  0001 C CNN
	1    2550 1000
	-1   0    0    1   
$EndComp
$Comp
L Connector_Generic:Conn_01x01 J3
U 1 1 5F1E394B
P 2550 1500
F 0 "J3" H 2550 1600 50  0000 C CNN
F 1 "L+" H 2700 1500 50  0000 C CNN
F 2 "Connector_Pin:Pin_D1.0mm_L10.0mm_LooseFit" H 2550 1500 50  0001 C CNN
F 3 "~" H 2550 1500 50  0001 C CNN
	1    2550 1500
	-1   0    0    1   
$EndComp
$Comp
L Device:L L1
U 1 1 5F1C70E1
P 2750 1250
F 0 "L1" H 2800 1150 50  0000 L CNN
F 1 "7.2u" H 2800 1250 50  0000 L CNN
F 2 "" H 2750 1250 50  0001 C CNN
F 3 "~" H 2750 1250 50  0001 C CNN
F 4 "760308101220" H 2800 1350 50  0000 L CNN "Part Number"
	1    2750 1250
	-1   0    0    1   
$EndComp
Wire Wire Line
	3000 1000 2750 1000
Wire Wire Line
	2750 1500 3000 1500
Wire Wire Line
	3000 1500 3000 1600
Connection ~ 3000 1500
Wire Wire Line
	3000 1000 3000 900 
Connection ~ 3000 1000
$Comp
L power:GND #PWR06
U 1 1 5F200AA8
P 3000 1600
F 0 "#PWR06" H 3000 1350 50  0001 C CNN
F 1 "GND" H 3005 1427 50  0000 C CNN
F 2 "" H 3000 1600 50  0001 C CNN
F 3 "" H 3000 1600 50  0001 C CNN
	1    3000 1600
	1    0    0    -1  
$EndComp
$Comp
L power:VAA #PWR07
U 1 1 5F201759
P 3600 900
F 0 "#PWR07" H 3600 750 50  0001 C CNN
F 1 "VAA" H 3615 1073 50  0000 C CNN
F 2 "" H 3600 900 50  0001 C CNN
F 3 "" H 3600 900 50  0001 C CNN
	1    3600 900 
	1    0    0    -1  
$EndComp
Wire Wire Line
	3600 900  3600 1100
Wire Wire Line
	8500 4000 9000 4000
$Comp
L Device:R R5
U 1 1 5F236486
P 9000 3750
F 0 "R5" H 9070 3796 50  0000 L CNN
F 1 "100k" H 9070 3705 50  0000 L CNN
F 2 "Resistor_SMD:R_0402_1005Metric" V 8930 3750 50  0001 C CNN
F 3 "~" H 9000 3750 50  0001 C CNN
	1    9000 3750
	1    0    0    -1  
$EndComp
$Comp
L Device:R R6
U 1 1 5F23BE87
P 9000 4250
F 0 "R6" H 9070 4296 50  0000 L CNN
F 1 "100k" H 9070 4205 50  0000 L CNN
F 2 "Resistor_SMD:R_0402_1005Metric" V 8930 4250 50  0001 C CNN
F 3 "~" H 9000 4250 50  0001 C CNN
	1    9000 4250
	1    0    0    -1  
$EndComp
Wire Wire Line
	9000 4100 9000 4000
Wire Wire Line
	9000 4000 9000 3900
Connection ~ 9000 4000
Wire Wire Line
	9000 4400 9000 4500
$Comp
L power:GND #PWR033
U 1 1 5F253150
P 9000 4500
F 0 "#PWR033" H 9000 4250 50  0001 C CNN
F 1 "GND" H 9005 4327 50  0000 C CNN
F 2 "" H 9000 4500 50  0001 C CNN
F 3 "" H 9000 4500 50  0001 C CNN
	1    9000 4500
	1    0    0    -1  
$EndComp
$Comp
L Device:D_Schottky_ALT D1
U 1 1 5F2DF607
P 1650 1500
F 0 "D1" H 1500 1450 50  0000 C CNN
F 1 "NSR05F30NXT5G" H 1650 1600 50  0000 C CNN
F 2 "Diode_SMD:D_0402_1005Metric" H 1650 1500 50  0001 C CNN
F 3 "https://static.chipdip.ru/lib/481/DOC004481842.pdf" H 1650 1500 50  0001 C CNN
	1    1650 1500
	-1   0    0    1   
$EndComp
$Comp
L power:GND #PWR01
U 1 1 5F2E20F8
P 1100 1700
F 0 "#PWR01" H 1100 1450 50  0001 C CNN
F 1 "GND" H 1105 1527 50  0000 C CNN
F 2 "" H 1100 1700 50  0001 C CNN
F 3 "" H 1100 1700 50  0001 C CNN
	1    1100 1700
	1    0    0    -1  
$EndComp
$Comp
L power:VDC #PWR02
U 1 1 5F2E632B
P 2000 1400
F 0 "#PWR02" H 2000 1300 50  0001 C CNN
F 1 "VDC" H 2015 1573 50  0000 C CNN
F 2 "" H 2000 1400 50  0001 C CNN
F 3 "" H 2000 1400 50  0001 C CNN
	1    2000 1400
	1    0    0    -1  
$EndComp
$Comp
L power:VAA #PWR05
U 1 1 5F303261
P 3000 900
F 0 "#PWR05" H 3000 750 50  0001 C CNN
F 1 "VAA" H 3015 1073 50  0000 C CNN
F 2 "" H 3000 900 50  0001 C CNN
F 3 "" H 3000 900 50  0001 C CNN
	1    3000 900 
	1    0    0    -1  
$EndComp
Wire Wire Line
	3800 1000 4200 1000
Wire Wire Line
	3600 1100 4200 1100
$Comp
L power:VDD #PWR09
U 1 1 5F343C69
P 4000 900
F 0 "#PWR09" H 4000 750 50  0001 C CNN
F 1 "VDD" H 4015 1073 50  0000 C CNN
F 2 "" H 4000 900 50  0001 C CNN
F 3 "" H 4000 900 50  0001 C CNN
	1    4000 900 
	1    0    0    -1  
$EndComp
Text Label 8500 1500 2    50   ~ 0
V_BAT_PROT
Text Label 1050 1500 0    50   ~ 0
VDC_IN
Wire Wire Line
	1000 1600 1100 1600
Wire Wire Line
	1000 1500 1500 1500
Wire Wire Line
	1800 1500 2000 1500
Wire Wire Line
	2000 1500 2000 1400
Text Label 8500 3500 0    50   ~ 0
V_BAT_PROT
Wire Wire Line
	8500 3500 9000 3500
Wire Wire Line
	9000 3500 9000 3600
$Comp
L MCU_Nordic:nRF52832-QFxx U1
U 1 1 5EF7C345
P 2400 5300
F 0 "U1" H 1650 6950 50  0000 C CNN
F 1 "nRF52832-QFxx" H 2900 3650 50  0000 C CNN
F 2 "Package_DFN_QFN:QFN-48-1EP_6x6mm_P0.4mm_EP4.6x4.6mm" H 2400 3200 50  0001 C CNN
F 3 "http://infocenter.nordicsemi.com/pdf/nRF52832_PS_v1.4.pdf" H 1900 5500 50  0001 C CNN
	1    2400 5300
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR04
U 1 1 5EF7F8A9
P 2400 7100
F 0 "#PWR04" H 2400 6850 50  0001 C CNN
F 1 "GND" H 2405 6927 50  0000 C CNN
F 2 "" H 2400 7100 50  0001 C CNN
F 3 "" H 2400 7100 50  0001 C CNN
	1    2400 7100
	1    0    0    -1  
$EndComp
Wire Wire Line
	2400 7100 2400 7000
Wire Wire Line
	1900 3600 1900 3500
Wire Wire Line
	2000 3600 2000 3400
Wire Wire Line
	2000 3400 1000 3400
Wire Wire Line
	1000 3500 1900 3500
Wire Wire Line
	2100 3600 2100 3300
Wire Wire Line
	2100 3300 1000 3300
Wire Wire Line
	2200 3600 2200 3200
Wire Wire Line
	2200 3200 1000 3200
Text Label 1000 3500 0    50   ~ 0
NRF_DEC1
Text Label 1000 3400 0    50   ~ 0
NRF_DEC2
Text Label 1000 3300 0    50   ~ 0
NRF_DEC3
Text Label 1000 3200 0    50   ~ 0
NRF_DEC4
Wire Wire Line
	2400 3600 2400 3500
$Comp
L power:+3.3V #PWR03
U 1 1 5EF93B76
P 2400 3500
F 0 "#PWR03" H 2400 3350 50  0001 C CNN
F 1 "+3.3V" H 2415 3673 50  0000 C CNN
F 2 "" H 2400 3500 50  0001 C CNN
F 3 "" H 2400 3500 50  0001 C CNN
	1    2400 3500
	1    0    0    -1  
$EndComp
Wire Wire Line
	2600 3600 2600 3200
Wire Wire Line
	2600 3200 2200 3200
Connection ~ 2200 3200
Text Label 1000 4600 0    50   ~ 0
NRF_ANT
Wire Wire Line
	1000 4600 1500 4600
Wire Wire Line
	1500 5900 1000 5900
Wire Wire Line
	1500 6000 1000 6000
Wire Wire Line
	1500 6100 1000 6100
Text Label 1000 5900 0    50   ~ 0
NRF_RESET
Text Label 1000 6000 0    50   ~ 0
NRF_SWDCLK
Text Label 1000 6100 0    50   ~ 0
NRF_SWDIO
Wire Wire Line
	1500 5100 1000 5100
Wire Wire Line
	1500 5500 1000 5500
Text Label 1000 5100 0    50   ~ 0
NRF_XC_P
Text Label 1000 5500 0    50   ~ 0
NRF_XC_N
Text Label 4000 5600 2    50   ~ 0
NRF_SWO
Wire Wire Line
	4000 5600 3300 5600
Wire Wire Line
	3300 4000 4000 4000
Text Label 4000 4000 2    50   ~ 0
NRF_BAT_LVL
Text Label 4000 4200 2    50   ~ 0
NRF_LED
Wire Wire Line
	4000 4200 3300 4200
Text Label 4000 4100 2    50   ~ 0
NRF_BTN
Wire Wire Line
	4000 4100 3300 4100
Text Label 4000 4300 2    50   ~ 0
NRF_PWR
Wire Wire Line
	4000 4300 3300 4300
NoConn ~ 3300 3800
NoConn ~ 3300 3900
NoConn ~ 3300 6800
NoConn ~ 3300 6700
NoConn ~ 3300 6600
NoConn ~ 3300 6500
NoConn ~ 3300 6400
NoConn ~ 3300 6300
NoConn ~ 3300 6200
NoConn ~ 3300 6100
NoConn ~ 3300 6000
NoConn ~ 3300 5900
NoConn ~ 3300 5800
NoConn ~ 3300 5700
NoConn ~ 3300 5500
NoConn ~ 3300 5400
NoConn ~ 3300 5300
NoConn ~ 3300 5200
NoConn ~ 3300 5100
NoConn ~ 3300 5000
NoConn ~ 3300 4900
NoConn ~ 3300 4800
NoConn ~ 3300 4700
NoConn ~ 3300 4600
NoConn ~ 3300 4500
NoConn ~ 3300 4400
$Comp
L Device:D_Schottky_ALT D2
U 1 1 5F0E620F
P 7750 1150
F 0 "D2" H 7600 1200 50  0000 C CNN
F 1 "NSR05F30NXT5G" H 7650 1100 50  0000 R CNN
F 2 "Diode_SMD:D_0402_1005Metric" H 7750 1150 50  0001 C CNN
F 3 "https://static.chipdip.ru/lib/481/DOC004481842.pdf" H 7750 1150 50  0001 C CNN
	1    7750 1150
	-1   0    0    1   
$EndComp
$Comp
L Switch:SW_Push_Lamp SW1
U 1 1 5F13E49F
P 7250 1250
F 0 "SW1" H 7350 1450 50  0000 C CNN
F 1 "Button" H 7450 1200 50  0000 C CNN
F 2 "chrns_passive:IT-1188E" H 7250 1550 50  0001 C CNN
F 3 "~" H 7250 1550 50  0001 C CNN
	1    7250 1250
	1    0    0    -1  
$EndComp
Wire Wire Line
	7450 1150 7500 1150
Wire Wire Line
	8000 1150 8000 1500
Wire Wire Line
	7450 1250 7650 1250
Wire Wire Line
	7650 1250 7650 1350
Wire Wire Line
	7650 1350 6950 1350
Wire Wire Line
	6950 1350 6950 1250
Wire Wire Line
	6950 1250 7050 1250
Wire Wire Line
	6950 1350 6850 1350
Connection ~ 6950 1350
$Comp
L power:GND #PWR029
U 1 1 5F1921B1
P 6850 1350
F 0 "#PWR029" H 6850 1100 50  0001 C CNN
F 1 "GND" H 6855 1177 50  0000 C CNN
F 2 "" H 6850 1350 50  0001 C CNN
F 3 "" H 6850 1350 50  0001 C CNN
	1    6850 1350
	0    1    1    0   
$EndComp
Connection ~ 7500 1150
Wire Wire Line
	7500 1150 7600 1150
Connection ~ 6950 1250
Connection ~ 6950 900 
Wire Wire Line
	6950 900  6950 1250
$Comp
L Device:Crystal_GND24 Y1
U 1 1 5F22E511
P 5500 3750
F 0 "Y1" V 5450 3250 50  0000 L CNN
F 1 "32 MHz" V 5550 3250 50  0000 L CNN
F 2 "Crystal:Crystal_SMD_2520-4Pin_2.5x2.0mm" H 5500 3750 50  0001 C CNN
F 3 "~" H 5500 3750 50  0001 C CNN
F 4 "NX2520SA" V 5550 3950 50  0000 L CNN "Part Number"
F 5 "±40 ppm" V 5450 4150 50  0000 C CNN "PPM"
	1    5500 3750
	0    1    1    0   
$EndComp
Wire Wire Line
	5700 3750 6100 3750
Connection ~ 6100 3750
Wire Wire Line
	6100 3750 6100 3500
Wire Wire Line
	5300 3750 5000 3750
$Comp
L power:GND #PWR016
U 1 1 5F256FC9
P 5000 3750
F 0 "#PWR016" H 5000 3500 50  0001 C CNN
F 1 "GND" H 5005 3577 50  0000 C CNN
F 2 "" H 5000 3750 50  0001 C CNN
F 3 "" H 5000 3750 50  0001 C CNN
	1    5000 3750
	0    1    1    0   
$EndComp
Wire Wire Line
	6750 1800 7100 1800
$Comp
L Device:R R1
U 1 1 5F27B8ED
P 7000 2100
F 0 "R1" V 6950 2250 50  0000 C CNN
F 1 "100k" V 7000 2100 50  0000 C CNN
F 2 "Resistor_SMD:R_0402_1005Metric" V 6930 2100 50  0001 C CNN
F 3 "~" H 7000 2100 50  0001 C CNN
	1    7000 2100
	0    1    1    0   
$EndComp
Wire Wire Line
	7150 2100 7500 2100
Wire Wire Line
	6750 1800 6750 2100
Wire Wire Line
	6850 2100 6750 2100
Wire Wire Line
	4800 2250 4800 2350
$Comp
L chrns_ldo:NCP170AXV330T2G U4
U 1 1 5F041FC9
P 10250 1500
F 0 "U4" H 10250 1750 50  0000 C CNN
F 1 "NCP170AXV330T2G" H 10250 1650 50  0000 C CNN
F 2 "Package_TO_SOT_SMD:SOT-563" H 10250 1650 50  0001 C CNN
F 3 "https://www.onsemi.com/pub/Collateral/NCP170-D.PDF" H 9950 1600 50  0001 C CNN
	1    10250 1500
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR034
U 1 1 5F05E71B
P 10250 2100
F 0 "#PWR034" H 10250 1850 50  0001 C CNN
F 1 "GND" H 10255 1927 50  0000 C CNN
F 2 "" H 10250 2100 50  0001 C CNN
F 3 "" H 10250 2100 50  0001 C CNN
	1    10250 2100
	1    0    0    -1  
$EndComp
Wire Wire Line
	10650 1500 10850 1500
Wire Wire Line
	10850 1500 10850 1600
Wire Wire Line
	9850 1600 9750 1600
Wire Wire Line
	9750 1600 9750 1500
Wire Wire Line
	9750 1500 9850 1500
Wire Wire Line
	9750 1500 9550 1500
Connection ~ 9750 1500
Wire Wire Line
	9550 1500 9550 1600
Text Label 9000 1500 0    50   ~ 0
V_BAT_PROT
Wire Wire Line
	9000 1500 9550 1500
Connection ~ 9550 1500
$Comp
L Device:C C14
U 1 1 5F0C4EB0
P 9550 1750
F 0 "C14" H 9400 1650 50  0000 L CNN
F 1 "1u" H 9400 1850 50  0000 L CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 9588 1600 50  0001 C CNN
F 3 "~" H 9550 1750 50  0001 C CNN
	1    9550 1750
	-1   0    0    1   
$EndComp
$Comp
L Device:C C15
U 1 1 5F0C68E5
P 10850 1750
F 0 "C15" H 10700 1650 50  0000 L CNN
F 1 "1u" H 10700 1850 50  0000 L CNN
F 2 "Capacitor_SMD:C_0603_1608Metric" H 10888 1600 50  0001 C CNN
F 3 "~" H 10850 1750 50  0001 C CNN
	1    10850 1750
	-1   0    0    1   
$EndComp
Wire Wire Line
	10850 1900 10850 2000
Wire Wire Line
	10250 1800 10250 2000
Wire Wire Line
	10250 2000 10850 2000
Wire Wire Line
	10250 2000 10150 2000
Wire Wire Line
	9550 2000 9550 1900
Connection ~ 10250 2000
Wire Wire Line
	10150 1800 10150 2000
Connection ~ 10150 2000
Wire Wire Line
	10150 2000 9550 2000
Wire Wire Line
	10250 2100 10250 2000
Wire Wire Line
	10850 1400 10850 1500
Connection ~ 10850 1500
$Comp
L power:+3.3V #PWR037
U 1 1 5F10BE76
P 10850 1400
F 0 "#PWR037" H 10850 1250 50  0001 C CNN
F 1 "+3.3V" H 10865 1573 50  0000 C CNN
F 2 "" H 10850 1400 50  0001 C CNN
F 3 "" H 10850 1400 50  0001 C CNN
	1    10850 1400
	1    0    0    -1  
$EndComp
Wire Wire Line
	5500 1700 5500 1400
Wire Wire Line
	4800 1900 4800 1850
Wire Wire Line
	4800 1900 4800 1950
Connection ~ 4800 1900
$EndSCHEMATC
