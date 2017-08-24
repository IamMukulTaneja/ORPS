package com.example.dell.orps;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class BookingDialogueFragment extends DialogFragment {
@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the Builder class for convenient dialog construction

 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
builder.setMessage("ARE YOU SURE ?")
        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface dialog, int id) {
    Intent i10=new Intent(getActivity(),PaymentActivity.class);
    i10.putExtra("vehiclenumber",getArguments().getString("vehnumber"));
    i10.putExtra("vehiclecolor",getArguments().getString("vehcolor"));
    i10.putExtra("vehiclename",getArguments().getString("vehname"));
    i10.putExtra("vehicletype",getArguments().getString("vehtype"));
    i10.putExtra("name",getArguments().getString("name"));
    i10.putExtra("email",getArguments().getString("email"));
    i10.putExtra("contact",getArguments().getString("contact"));
    i10.putExtra("station",getArguments().getString("station"));
    startActivity(i10);
}
})
.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface dialog, int id) {
    Intent i10=new Intent(getActivity(),VehicleDetails.class);
}
});
// Create the AlertDialog object and return it
return builder.create(); }
}