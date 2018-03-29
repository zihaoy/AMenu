package lab6.zihaoy.amenu;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.preference.PreferenceManager;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,RatingBar.OnRatingBarChangeListener {
    private Button NewOrder;
    private Button Check;
    private SharedPreferences prefs;
    private RatingBar Rating;
    private CheckBox Renew;

    //I use intent for this two
    public int MaxCal = 1000000000;
    public int MaxPrice = 1000000000;

    public static int price = 0;
    public static int cal = 0;
    public static String order = "";
    public static int item = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        NewOrder = (Button)findViewById(R.id.NewOrder);
        Check = (Button)findViewById(R.id.Check);
        Rating = (RatingBar)findViewById(R.id.ratingBar);
        Renew = (CheckBox)findViewById(R.id.checkBox);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        //For setting
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //set listenner while click
        NewOrder.setOnClickListener(this);
        Check.setOnClickListener(this);
        Rating.setOnRatingBarChangeListener(this);
        //if it's land, we will show the check
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            run();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        //getting MaxPrice and MaxCal from Setting
        MaxPrice = Integer.parseInt(prefs.getString("MaxPrice","1000000000"));
        MaxCal = Integer.parseInt(prefs.getString("MaxCal","1000000000"));
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            run();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //a new activity for setting
            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //if the check box is click, we will renew all order
            case R.id.NewOrder:
                if(Renew.isChecked()){
                    price = 0;
                    cal = 0;
                    order = "";
                    item = 0;
                }
                Intent intent = new Intent(this, MenuActivity.class);
                //give this two value to Menu
                intent.putExtra("Maxcal",Integer.toString(MaxCal));
                intent.putExtra("Maxprice",Integer.toString(MaxPrice));
                this.startActivity(intent);
                break;
            //We can check here
            case R.id.Check:
                Intent intent2 = new Intent(this, CheckActivity.class);
                this.startActivity(intent2);
                break;


        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        String rate = Float.toString(Rating.getRating());
        Toast.makeText(this, "Thanks for give us " + rate + " points",
                Toast.LENGTH_SHORT).show();
    }
    //run method which use to load the check while screen land
    public void run(){
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.listview_itemt,
                new String[]{"name","price"},
                new int[]{R.id.NameT,R.id.PriceT});
        ListView itemsListViewT = (ListView)findViewById( R.id.itemsListViewT);
        itemsListViewT.setAdapter(adapter);
        TextView PriceView = (TextView)findViewById(R.id.PricetextView);
        PriceView.setText("Current Price: $  " + price);
    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();

        int i = 0;
        while(i < MainActivity.order.length()){
            switch (MainActivity.order.charAt(i)){
                case '1':
                    map = new HashMap<String, Object>();
                    map.put("name", "pizza");
                    map.put("price","$10 200cal");
                    list.add(map);
                    break;
                case '2':
                    map = new HashMap<String, Object>();
                    map.put("name", "icecream");
                    map.put("price","$5 100cal");
                    list.add(map);
                    break;
                case '3':
                    map = new HashMap<String, Object>();
                    map.put("name", "beef");
                    map.put("price","$15 300cal");
                    list.add(map);
                    break;
                case '4':
                    map = new HashMap<String, Object>();
                    map.put("name", "egg");
                    map.put("price","$1 20cal");
                    list.add(map);
                    break;
                case '5':
                    map = new HashMap<String, Object>();
                    map.put("name", "noodle");
                    map.put("price","$12 150cal");
                    list.add(map);
                    break;
                case '6':
                    map = new HashMap<String, Object>();
                    map.put("name", "pork");
                    map.put("price","$10 200cal");
                    list.add(map);
                    break;
                case '7':
                    map = new HashMap<String, Object>();
                    map.put("name", "riceball");
                    map.put("price","$5 20cal");
                    list.add(map);
                    break;
                case '8':
                    map = new HashMap<String, Object>();
                    map.put("name", "seafood");
                    map.put("price","$15 300cal");
                    list.add(map);
                    break;
            }
            i+=1;
        }

        return list;
    }

}
