package com.example.semana03_pc1_p4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2, num3, num4, num5;
    CheckBox checkVer;
    TextView txtResult;
    Button btnCalcular, btnLimpiar;
    Double coefic, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.editTextNumber1);
        num2 = findViewById(R.id.editTextNumber2);
        num3 = findViewById(R.id.editTextNumber3);
        num4 = findViewById(R.id.editTextNumber4);
        num5 = findViewById(R.id.editTextNumber5);
        checkVer = findViewById(R.id.checkBoxVer);
        txtResult = findViewById(R.id.txtResultado);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String valor1 = num1.getText().toString();
                String valor2 = num2.getText().toString();
                String valor3 = num3.getText().toString();
                String valor4 = num4.getText().toString();
                String valor5 = num5.getText().toString();

                if (valor1.isEmpty() || valor2.isEmpty() || valor3.isEmpty() || valor4.isEmpty() || valor5.isEmpty()) {
                    txtResult.setText("Complete todos los campos");
                    return;
                }

                Integer totalPob = Integer.parseInt(valor1);
                Double segur = Double.parseDouble(valor2);
                Double porciEsp = Double.parseDouble(valor3) / 100;
                Double porciNE = Double.parseDouble(valor4) / 100;
                Double precision = Double.parseDouble(valor5) / 100;

                if (segur == 90) {
                    coefic = 1.645;
                } else if (segur == 95) {
                    coefic = 1.96;
                } else if (segur == 97.5) {
                    coefic = 2.24;
                } else if (segur == 99) {
                    coefic = 2.576;
                } else {
                    coefic = 0.0;
                }

                resultado = (totalPob*Math.pow(coefic,2)*porciEsp*porciNE) /
                        ((Math.pow(precision,2)*(totalPob-1))+(Math.pow(coefic,2)*porciEsp*porciNE));

            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1.setText("");
                num2.setText("");
                num3.setText("");
                num4.setText("");
                num5.setText("");
                checkVer.setChecked(false);
                txtResult.setText("");
                resultado = 0.0;
            }
        });
    }

    private String getResultado(Object resultado) {
        final String txt_resultado = "%s";
        return String.format(txt_resultado, resultado);
    }

    public void mostrarResultado(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (view.getId() == R.id.checkBoxVer) {
            if (checked) {
                txtResult.setText(getResultado(Math.round(resultado)));
                return;
            }
            else {
                txtResult.setText("");
            }
        }
    }
}