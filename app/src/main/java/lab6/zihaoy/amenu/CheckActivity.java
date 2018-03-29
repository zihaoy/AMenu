package lab6.zihaoy.amenu;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JimYu on 2017/7/19.
 */


public class CheckActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView itemsListView;
    private TextView Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);;
        itemsListView = (ListView)findViewById(R.id.itemsListViewT);
        Text = (TextView)findViewById(R.id.PriceTextView);
        run();
        itemsListView.setOnItemClickListener(this);
    }
    //run method which use to load the check while screen land
    public void run(){
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.listview_itemt,
                new String[]{"name","price"},
                new int[]{R.id.NameT,R.id.PriceT});
        itemsListView.setAdapter(adapter);
        String temp = "$"+Integer.toString(MainActivity.price)+ "          " + Integer.toString(MainActivity.cal) + "  cal";
        Text.setText(temp);
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
    //use to go back home
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //use to delete item
    public void deleteMenu(int position){

        switch (MainActivity.order.charAt(position)){
            case '1':
                MainActivity.price -= 10;
                MainActivity.cal -= 200;
                break;
            case '2':
                MainActivity.price -= 5;
                MainActivity.cal -= 100;
                break;
            case '3':
                MainActivity.price -= 15;
                MainActivity.cal -= 300;
                break;
            case '4':
                MainActivity.price -= 1;
                MainActivity.cal -= 20;
                break;
            case '5':
                MainActivity.price -= 12;
                MainActivity.cal -= 150;
                break;
            case '6':
                MainActivity.price -= 10;
                MainActivity.cal -= 200;
                break;
            case '7':
                MainActivity.price -= 5;
                MainActivity.cal -= 20;
                break;
            case '8':
                MainActivity.price -= 15;
                MainActivity.cal -= 300;
                break;

        }
        int i=0;
        String temp = "";
        while(i<MainActivity.order.length()){
            if(i != position){
                temp += MainActivity.order.charAt(i);
            }
            i+=1;
        }
        MainActivity.order = temp;


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        deleteMenu(position);
        run();
    }

}
