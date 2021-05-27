package co.edu.unab.castellanos.jose.restaurantfastfood_admin.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.edu.unab.castellanos.jose.restaurantfastfood_admin.model.entity.Product;
import co.edu.unab.castellanos.jose.restaurantfastfood_admin.model.network.CallBackApp;
import co.edu.unab.castellanos.jose.restaurantfastfood_admin.view.repository.ProductRepository;
import co.edu.unab.castellanos.jose.restaurantfastfood_admin.R;

public class FormularioActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etType;
    private EditText etPrice;
    private EditText etPicture;
    private EditText etDescripcion;
    private Button btFormulario;
    private ProductRepository productoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        asociarElementos();

        productoRepository = new ProductRepository(FormularioActivity.this);

        btFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String type = etType.getText().toString();
                double price = Double.parseDouble(etPrice.getText().toString());
                String imagen = etPicture.getText().toString();
                String descripcion = etDescripcion.getText().toString();

                Product producto = new Product(type, name, price, descripcion, imagen);

                productoRepository.agregarProductosFS(producto, new CallBackApp<Boolean>() {
                    @Override
                    public void correct(Boolean respuesta) {
                        if (respuesta) {
//                            Intent datos = new Intent();
////                            datos.putExtra("product", producto);
////                            setResult(RESULT_OK, datos);
                            finish();

                        } else {

                        }
                    }

                    @Override
                    public void error(Exception error) {

                    }
                });


            }
        });
    }
    private void asociarElementos(){
        etType = findViewById(R.id.et_product_type);
        etName = findViewById(R.id.et_product_name);
        etPrice = findViewById(R.id.et_product_price);
        etPicture = findViewById(R.id.et_product_picture);
        etDescripcion = findViewById(R.id.et_product_descripcion);
        btFormulario = findViewById(R.id.bt_add_product);
    }
}