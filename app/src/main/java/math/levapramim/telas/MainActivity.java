package math.levapramim.telas;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import math.levapramim.R;
import math.levapramim.dao.VeiculoDAO;
import math.levapramim.modelo.Veiculo;

public class MainActivity extends AppCompatActivity {

    private VeiculoDAO veiculoDAO;
    private static Context c;
    private EditText marca;
    private EditText modelo;
    private EditText cor;
    private EditText placa;
    private Button botao;
    private Veiculo veiculo;

    public static Context showContext(){
        return c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = this.getApplicationContext();

        CharSequence e = "e";
        Toast.makeText(this, "I'm going to hell", Toast.LENGTH_LONG).show();

        System.out.println("I'm goig to HELL");

        veiculoDAO = new VeiculoDAO();
        marca = findViewById(R.id.editText);
        modelo = findViewById(R.id.editText2);
        cor = findViewById(R.id.editText3);
        placa = findViewById(R.id.editText4);
        botao = findViewById(R.id.button2);
        veiculo = new Veiculo();

        botao.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         System.out.println("clicou");
                                         veiculo.setPlaca(placa.getText().toString());
                                         veiculo.setCor(cor.getText().toString());
                                         veiculo.setModelo(modelo.getText().toString());
                                         veiculo.setMarca(marca.getText().toString());
                                         veiculoDAO.inserir(veiculo);
                                     }
                                 }
        );

    }
}
