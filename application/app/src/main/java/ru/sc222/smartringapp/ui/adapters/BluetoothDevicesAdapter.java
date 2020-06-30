package ru.sc222.smartringapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.dto.AdapterBluetoothDevice;
import ru.sc222.smartringapp.utils.PreferenceUtils;

public class BluetoothDevicesAdapter extends RecyclerView.Adapter<BluetoothDevicesAdapter.ViewHolder> {

    private List<AdapterBluetoothDevice> mData;
    private LayoutInflater mInflater;
    private BluetoothDevicesAdapter.ItemClickListener mClickListener;
    private Context c;
    private String currentDeviceAddress;

    // data is passed into the constructor
    public BluetoothDevicesAdapter(Context context, List<AdapterBluetoothDevice> data) {
        c = context;
        currentDeviceAddress = PreferenceUtils.getCurrentDeviceAddress(c);
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public BluetoothDevicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.bluetooth_devices_item, parent, false);
        return new BluetoothDevicesAdapter.ViewHolder(view);
    }

    // binds the data to the imageview in each cell
    @Override
    public void onBindViewHolder(@NonNull BluetoothDevicesAdapter.ViewHolder holder, int position) {
        //holder.imageButton.setImageResource(mData[position]);
        if (mData.get(position).getAddress().equals(currentDeviceAddress)) {
            holder.textName.setTextColor(c.getResources().getColor(R.color.colorGreen));
        } else {
            holder.textName.setTextColor(c.getResources().getColor(R.color.colorPrimary));
        }


        holder.textAddress.setText(mData.get(position).getAddress());
        String name = mData.get(position).getName();
        if (name == null || name.isEmpty()) {
            name = c.getResources().getString(R.string.unknown_device);
        }
        holder.textName.setText(name);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    public AdapterBluetoothDevice getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(BluetoothDevicesAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatTextView textName;
        AppCompatTextView textAddress;
        CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.device_name);
            textAddress = itemView.findViewById(R.id.device_address);
            card = itemView.findViewById(R.id.card_container);
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
