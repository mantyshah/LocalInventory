package com.inventory.manthanshah.localinventory;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.inventory.manthanshah.localinventory.data.ProductContract.ProductEntry;

public class ProductCursorAdapter  extends CursorAdapter{

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView productName, productPrize, supplierName;

        productName = view.findViewById(R.id.productName);
        productPrize = view.findViewById(R.id.productPrize);
        supplierName = view.findViewById(R.id.supplierName);

        String name = cursor.getString(cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME));
        String prize = cursor.getString(cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRIZE));
        String supplier = cursor.getString(cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_Name));

        productName.setText(name);
        productPrize.setText(prize);
        supplierName.setText(supplier);

    }
}
