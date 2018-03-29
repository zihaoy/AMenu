package lab6.zihaoy.amenu;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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


public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView itemsListView;
    private TextView Text;
    private int MaxCal;
    private int MaxPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //get Maxcal and max price from intent
        Intent intent = getIntent();
        MaxCal = Integer.parseInt(intent.getStringExtra("Maxcal"));
        MaxPrice = Integer.parseInt(intent.getStringExtra("Maxprice"));
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.listview_item,
                new String[]{"pic","name","price"},
                new int[]{R.id.Pic,R.id.Name,R.id.Price});
        ListView itemsListView = (ListView)findViewById(R.id.itemsListView);
        Text = (TextView)findViewById(R.id.titleTextView);
        itemsListView.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        itemsListView.setOnItemClickListener(this);

    }
    @Override
    public void onResume(){
        super.onResume();
        //make sure will not change will rotate
        String temp = "$"+Integer.toString(MainActivity.price)+ "    " + Integer.toString(MainActivity.cal) + "  cal";
        Text.setText(temp);
    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pic", R.drawable.pizza);
        map.put("name", "pizza");
        map.put("price","$10 200cal");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("pic", R.drawable.icecream);
        map.put("name", "icecream");
        map.put("price","$5 100cal");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("pic", R.drawable.beef);
        map.put("name", "beef");
        map.put("price","$15 300cal");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("pic", R.drawable.egg);
        map.put("name", "egg");
        map.put("price","$1 20cal");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("pic", R.drawable.noodle);
        map.put("name", "noodle");
        map.put("price","$12 150cal");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("pic", R.drawable.pork);
        map.put("name", "pork");
        map.put("price","$10 200cal");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("pic", R.drawable.riceball);
        map.put("name", "riceball");
        map.put("price","$5 20cal");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("pic", R.drawable.seafood);
        map.put("name", "seafood");
        map.put("price","$15 300cal");
        list.add(map);

        return list;
    }
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
    // use to add menu
    public String AddMenu(int price,int cal,String order){
        if(MainActivity.cal + cal <= MaxCal && MainActivity.price + price<= MaxPrice){
            MainActivity.order = MainActivity.order + order;
            MainActivity.price += price;
            MainActivity.cal += cal;
            MainActivity.item+=1;
            String temp = "$"+Integer.toString(MainActivity.price)+ "    " + Integer.toString(MainActivity.cal) + "  cal";
            return temp;
        }
        return "can't add any more";
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Text.setText(AddMenu(10,200,"1"));
                break;
            case 1:
                Text.setText(AddMenu(5,100,"2"));
                break;
            case 2:
                Text.setText(AddMenu(15,300,"3"));
                break;
            case 3:
                Text.setText(AddMenu(1,20,"4"));
                break;
            case 4:
                Text.setText(AddMenu(12,150,"5"));
                break;
            case 5:
                Text.setText(AddMenu(10,200,"6"));
                break;
            case 6:
                Text.setText(AddMenu(5,20,"7"));
                break;
            case 7:
                Text.setText(AddMenu(15,300,"8"));
                break;
        }
    }


}
