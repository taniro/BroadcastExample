package br.com.ufrn.eaj.tads.broadcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TesteReceiverAPI extends BroadcastReceiver {
    public TesteReceiverAPI() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("Script", "Mensagem recebida, com API!!");


    }
}
