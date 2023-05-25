package sg.edu.rp.c346.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvDBS;
    TextView tvOCBC;
    TextView tvUOB;
    String bankClicked = "";
    String url = "";
    String phoneNo = "";
    boolean favDbs = false, favOcbc = false, favUob = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDBS = findViewById(R.id.textViewDBS);
        tvOCBC = findViewById(R.id.textViewOCBC);
        tvUOB = findViewById(R.id.textViewUOB);

        registerForContextMenu(tvDBS);
        registerForContextMenu(tvOCBC);
        registerForContextMenu(tvUOB);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == tvDBS) {
            bankClicked = "DBS";
        } else if (v == tvOCBC) {
            bankClicked = "OCBC";
        } else if (v == tvUOB) {
            bankClicked = "UOB";
        } else {
            bankClicked = "";
        }
        menu.add(0, 0, 0, "Website");
        menu.add(0, 1, 1, "Contact the bank");
        menu.add(0, 2, 2, "Toggle Favourite");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView tvSelected = null;
        if (bankClicked.equalsIgnoreCase("DBS")) {
            url = "https://www.dbs.com.sg";
            phoneNo = "18001111111";
            tvSelected = tvDBS;


        } else if (bankClicked.equalsIgnoreCase("OCBC")) {
            url = "https://www.ocbc.com";
            phoneNo = "18003633333";
            tvSelected = tvOCBC;

        } else if (bankClicked.equalsIgnoreCase("UOB")) {
            url = "https://www.uob.com.sg";
            phoneNo = "18002222121";
            tvSelected = tvUOB;

        } else {
            url = "";
            phoneNo = "";
        }

        if (item.getItemId() == 0) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else if (item.getItemId() == 1) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNo));
            startActivity(intent);
        }
        else if (item.getItemId() == 2) {
            if(tvSelected == tvDBS){
                favDbs = !favDbs;
                if(favDbs){
                    tvSelected.setTextColor(Color.RED);
                }
                else{
                    tvSelected.setTextColor(Color.BLACK);
                }
            }

            if(tvSelected == tvOCBC){
                favOcbc = !favOcbc;
                if(favOcbc){
                    tvSelected.setTextColor(Color.RED);
                }
                else{
                    tvSelected.setTextColor(Color.BLACK);
                }
            }

            if(tvSelected == tvUOB){
                favUob = !favUob;
                if(favDbs){
                    tvSelected.setTextColor(Color.RED);
                }
                else{
                    tvSelected.setTextColor(Color.BLACK);
                }
            }
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_banks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.EnglishSelection) {
            tvDBS.setText(getString(R.string.dbs));
            tvOCBC.setText("OCBC");
            tvUOB.setText("UOB");
            return true;

        } else if (item.getItemId() == R.id.ChineseSelection) {
            tvDBS.setText(getString(R.string.dbs_zh));
            tvOCBC.setText("华侨银行");
            tvUOB.setText("大华银行");
            return true;

        } else if (item.getItemId() == R.id.MalaySelection) {
            tvDBS.setText("Bank Pembangunan Singapura Berhad");
            tvOCBC.setText("Perbadanan Perbankan Cina Luar Negara");
            tvUOB.setText("Bank Luar Negara Bersatu");
            return true;

        } else if (item.getItemId() == R.id.TamilSelection) {
            tvDBS.setText("சிங்கப்பூர் வளர்ச்சி வங்கி லிமிடெட்");
            tvOCBC.setText("வெளிநாட்டு சீன வங்கி நிறுவனம்");
            tvUOB.setText("யுனைடெட் ஓவர்சீஸ் வங்கி");
            return true;


        } else {
            Log.e("onOptionsItemSelected", "Error, neither English nor Chinese");
        }

        return super.onOptionsItemSelected(item);
    }
}