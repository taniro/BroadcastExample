package br.com.ufrn.eaj.tads.broadcastexample;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver meu_receiver = new TesteReceiverAPI();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        registerReceiver(meu_receiver, new IntentFilter("BANG"));

        // Solicita as permissões
        String[] permissoes = new String[]{
                Manifest.permission.READ_SMS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.RECEIVE_SMS
        };
        PermissionUtils.validate(this, 0, permissoes);
    }

    public void onClickEstatico( View v){

        Intent i = new Intent("BINGO");
        sendBroadcast(i);
        Toast.makeText(this, "Intent enviada!", Toast.LENGTH_SHORT).show();
    }

    public void onClickDinamico( View v){

        Intent i = new Intent("BANG");
        sendBroadcast(i);
        Toast.makeText(this, "Intent enviada  por API!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(meu_receiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                // Alguma permissão foi negada, agora é com você :-)
                alertAndFinish();
                return;
            }
        }
        // Se chegou aqui está OK :-)
    }

    private void alertAndFinish() {
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name).setMessage("Para utilizar este aplicativo, você precisa aceitar as permissões.");
            // Add the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            android.support.v7.app.AlertDialog dialog = builder.create();
            dialog.show();

        }
    }
}
