package com.wgetdc.disqueriaseccion2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class detalleActivity extends AppCompatActivity {

    EditText edtDetalle_id;
    TextView txtDetalle_artista, txtDetalle_album, txtDetalle_fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        edtDetalle_id = findViewById(R.id.edtDetalle_id);
        txtDetalle_artista = findViewById(R.id.txtDetalle_artista);
        txtDetalle_album = findViewById(R.id.txtDetalle_album);
        txtDetalle_fecha = findViewById(R.id.txtDetalle_fecha);
    }

    public void buscarDisco(View view){
        gestorDeBaseDeDatos gestor = new gestorDeBaseDeDatos(this, "disquetera", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String id = edtDetalle_id.getText().toString();

        Cursor datos = db.rawQuery("select artistas, album, fecha from discos where id =" + id, null);
        if (datos.moveToFirst()){

            txtDetalle_artista.setText(datos.getString(0).toString());
            txtDetalle_album.setText(datos.getString(1).toString());
            txtDetalle_fecha.setText(datos.getString(2).toString());

            edtDetalle_id.setText("");
            db.close();
        }else{
            Toast.makeText(this, "No existen registros asociados a este id", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void eliminarDisco(View view){
        gestorDeBaseDeDatos gestor = new gestorDeBaseDeDatos(this, "disquetera", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String id = edtDetalle_id.getText().toString();

        if (!id.isEmpty()){
            int filas = db.delete("discos", "id=" + id,null);

            if (filas == 1){
                Toast.makeText(this, "Se ha eliminado el disco", Toast.LENGTH_LONG).show();
                db.close();
                txtDetalle_artista.setText("Artista:");
                txtDetalle_album.setText("Album:");
                txtDetalle_fecha.setText("Fecha:");
                edtDetalle_id.setText("");
            }else {
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }

    
}