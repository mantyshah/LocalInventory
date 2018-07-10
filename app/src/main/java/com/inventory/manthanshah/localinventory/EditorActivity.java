package com.inventory.manthanshah.localinventory;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.inventory.manthanshah.localinventory.data.ProductDBHelper;
import com.inventory.manthanshah.localinventory.data.ProductContract.ProductEntry;

public class EditorActivity extends AppCompatActivity implements View.OnClickListener {

    EditText productName, productPrize, supplierName, supplierPhone ;
    TextView  quantityProduct;
    Button increaseQuantity, decreaseQuantity;
    private ProductDBHelper mProductDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);


        mProductDBHelper = new ProductDBHelper(this);

        productName = findViewById(R.id.productNameEditText);
        productPrize = findViewById(R.id.productPrizeEditText);
        quantityProduct = findViewById(R.id.quantity_edit);
        quantityProduct.setText("0");
        supplierName = findViewById(R.id.supplierNameEditText);
        supplierPhone = findViewById(R.id.supplierPhoneEditText);
        increaseQuantity = findViewById(R.id.increase_quantity);
        decreaseQuantity = findViewById(R.id.decrease_quantity);

        increaseQuantity.setOnClickListener(this);
        decreaseQuantity.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.increase_quantity:
                addOneToQuantity();
            break;
            case R.id.decrease_quantity:
                subtractOneToQuantity();
            break;
        }
    }

    private void addOneToQuantity() {
        String previousValueString = quantityProduct.getText().toString();
        int previousValue;
        if (previousValueString.isEmpty()) {
            previousValue = 0;
        } else {
            previousValue = Integer.parseInt(previousValueString);
        }
        quantityProduct.setText(String.valueOf(previousValue + 1));
    }

    public void subtractOneToQuantity(){
        String previousValueString = quantityProduct.getText().toString();
        int previousValue;
        if (previousValueString.isEmpty()) {
            return;
        } else if (previousValueString.equals("0")) {
            return;
        } else {
            previousValue = Integer.parseInt(previousValueString);
            quantityProduct.setText(String.valueOf(previousValue - 1));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                String mProductName = productName.getText().toString().trim();
                String mProductPrize = productPrize.getText().toString().trim();
                int mQuantityProduct;
                if(quantityProduct.getText().toString().trim().isEmpty()) {
                    mQuantityProduct = 0;
                }
                else{
                    mQuantityProduct = Integer.parseInt(quantityProduct.getText().toString().trim());
                }
                String mSupplierName = supplierName.getText().toString().trim();
                String mSupplierPhone = supplierPhone.getText().toString().trim();

                // Save Product to DataBase
                SQLiteDatabase database = mProductDBHelper.getWritableDatabase();

                if(!TextUtils.isEmpty(mProductName))
                {
                    if(!TextUtils.isEmpty(mProductPrize))
                    {
                        if(!TextUtils.isEmpty(mSupplierName))
                        {
                            ContentValues values = new ContentValues();
                            values.put(ProductEntry.COLUMN_PRODUCT_NAME, mProductName);
                            values.put(ProductEntry.COLUMN_PRODUCT_PRIZE, mProductPrize);
                            values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, mQuantityProduct);
                            values.put(ProductEntry.COLUMN_SUPPLIER_Name, mSupplierName);
                            values.put(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER, mSupplierPhone);

                            long id = database.insert(ProductEntry.TABLE_NAME, null, values);
                            Log.d("Database Insert : ", String.valueOf(id));
                            if (id != -1) {
                                Intent i = new Intent(EditorActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Entry was not made.", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Please entre supplier name", Toast.LENGTH_SHORT).show();
                            supplierName.setFocusable(true);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please entre product prize", Toast.LENGTH_SHORT).show();
                        productPrize.setFocusable(true);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please entre product name", Toast.LENGTH_SHORT).show();
                    productName.setFocusable(true);
                }


                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:

                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                Intent i = new Intent(EditorActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
