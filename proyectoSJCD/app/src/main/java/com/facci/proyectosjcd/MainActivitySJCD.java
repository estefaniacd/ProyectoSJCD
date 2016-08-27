package com.facci.proyectosjcd;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivitySJCD extends AppCompatActivity {
    DBhelper  dbSQLITE;
    EditText ID, txtNombre, txtApellido, txtRecintoElectoral, txtAñoNacimiento;
    Button btnIngresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_sjcd);

        dbSQLITE = new DBhelper(this);
    }
    public void insertar(View v) {

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtRecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAñoNacimiento = (EditText) findViewById(R.id.txtAñoNacimiento);

        boolean Insertadatos = dbSQLITE.Insertar(txtNombre.getText().toString(), txtApellido.getText().toString(), txtRecintoElectoral.getText().toString(), Integer.parseInt(txtAñoNacimiento.getText().toString()));

        if (Insertadatos) {
            Toast.makeText(MainActivitySJCD.this, "Datos ingresados con exito", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(MainActivitySJCD.this,"Datos no ingresados, ocurrió un error",Toast.LENGTH_SHORT).show();}
    }
    public void buscar (View v) {
        Cursor res = dbSQLITE.VerTodos();

        if (res.getCount() == 0) {
            Mensaje("Error","No se encontraron registros");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+res.getString(3)+"\n");
            buffer.append("Año de Nacimiento : "+res.getInt(4)+"\n\n");
        }
        Mensaje("Registros",buffer.toString());
    }
    private void Mensaje (String titulo, String Mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }
    public void modificar (View v) {
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido= (EditText) findViewById(R.id.txtApellido);
        txtRecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAñoNacimiento = (EditText) findViewById(R.id.txtAñoNacimiento);
        ID = (EditText) findViewById(R.id.txtID);
        boolean ActualizandoDatos = dbSQLITE.Modificar(ID.getText().toString(), txtNombre.getText().toString(), txtApellido.getText().toString(), txtRecintoElectoral.getText().toString(), Integer.parseInt(txtAñoNacimiento.getText().toString()));
        if(ActualizandoDatos)
            Toast.makeText(MainActivitySJCD.this,"Datos Ingresados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivitySJCD.this,"Hubo un error", Toast.LENGTH_SHORT).show();

    }
    public void eliminar (View v) {
        ID = (EditText) findViewById(R.id.txtID);

        Integer registrosEliminados = dbSQLITE.Eliminar(ID.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivitySJCD.this,"Registros Eliminados",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivitySJCD.this,"Registros no eliminados",Toast.LENGTH_SHORT).show();
        }
    }



}
