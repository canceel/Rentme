package com.allipper.rentme.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.adapter.CurrentCityAdapter;
import com.allipper.rentme.bean.BeanCountry;
import com.allipper.rentme.common.view.SideBar;
import com.allipper.rentme.database.DbManager;
import com.allipper.rentme.ui.base.BaseActivity;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by allipper on 2015/7/20.
 */
public class CurrentCityActivity extends BaseActivity implements
        AdapterView.OnItemClickListener, AbsListView.OnScrollListener, View.OnTouchListener,
        AMapLocationListener {
    public final static int CURRENT_ACTIVITY_RESULT = 10;

    public static final int TYPE_LOCATION = 0;
    public static final int TYPE_SELECT = 1;

    private LinearLayout titleLayout;
    private TextView select_list_title;
    private ListView lvSelectList;
    private RelativeLayout select_toast_layout;
    private TextView section_toast_text;
    private TextView tvPlace;
    private SideBar select_alphabetButton;

    private LocationManagerProxy mLocationManagerProxy;
    private DbManager manager;
    private List<String> alphabet;
    private List<BeanCountry> datas = new ArrayList<>();
    private CurrentCityAdapter adapter;
    private int lastFirstVisibleItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_city);
        initView();
    }

    private void initView() {
        lvSelectList = (ListView) findViewById(R.id.select_list);
        tvPlace = (TextView) findViewById(R.id.place);
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        select_list_title = (TextView) findViewById(R.id.select_list_title);
        select_alphabetButton = (SideBar) findViewById(R.id.select_alphabetButton);
        select_toast_layout = (RelativeLayout) findViewById(R.id.select_toast_layout);
        section_toast_text = (TextView) findViewById(R.id.section_toast_text);

        manager = new DbManager(this);
        datas = manager.queryAllCities();
        alphabet = setAlphabet(datas);
        adapter = new CurrentCityAdapter(this, datas, alphabet);
        lvSelectList.setAdapter(adapter);
        lvSelectList.setOnScrollListener(this);
        lvSelectList.setOnItemClickListener(this);
        select_alphabetButton.setOnTouchListener(this);
        select_alphabetButton.setAlphabet(alphabet);
    }

    private List<String> setAlphabet(List<BeanCountry> datas) {
        List<String> alphabet = new ArrayList<String>();
        String name = "";
        for (BeanCountry temp : datas) {
            String str = temp.acronym.substring(0, 1);
            if (!name.equals(str)) {
                alphabet.add(str);
                name = str;
            }
        }
        return alphabet;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        saveCountry(datas.get(position).acronymName, TYPE_SELECT);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int
            totalItemCount) {
        int section = adapter.getSectionForPosition(firstVisibleItem);
        int nextSecPosition = adapter.getPositionForSection(section + 1);
        if (firstVisibleItem != lastFirstVisibleItem) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                    .getLayoutParams();
            params.topMargin = 0;
            titleLayout.setLayoutParams(params);
            select_list_title.setText(alphabet.get(section).substring(0, 1));
            select_alphabetButton.setCurrentItem(section);
        }
        if (nextSecPosition == firstVisibleItem + 1) {
            View childView = view.getChildAt(0);
            if (childView != null) {
                int titleHeight = titleLayout.getHeight();
                int bottom = childView.getBottom();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                        .getLayoutParams();
                if (bottom < titleHeight) {
                    float pushedDistance = bottom - titleHeight;
                    params.topMargin = (int) pushedDistance;
                    titleLayout.setLayoutParams(params);
                } else {
                    if (params.topMargin != 0) {
                        params.topMargin = 0;
                        titleLayout.setLayoutParams(params);
                    }
                }
            }
        }
        lastFirstVisibleItem = firstVisibleItem;
    }

    public void location(View view) {
        init();
    }

    /**
     * 初始化定位
     */
    private synchronized void init() {
        tvPlace.setText("正在获取当前城市，请稍后...");

        mLocationManagerProxy = LocationManagerProxy.getInstance(this);

        //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
        //在定位结束后，在合适的生命周期调用destroy()方法
        //其中如果间隔时间为-1，则定位只定一次
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, -1, 15, this);

        mLocationManagerProxy.setGpsEnable(false);
    }

    /**
     * 保存定位或选择的地址到SharedPreference中
     */
    private void saveCountry(String searchCondition, int type) {

        BeanCountry beanCountry = manager.queryCountryByCondition(searchCondition, type);
//        Utils.saveLocationCode(mContext, beanCountry.acronymName, beanCountry.provinceCode,
//                beanCountry.cityCode, beanCountry.districtCode);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("city", beanCountry.acronymName);
        setResult(RESULT_OK, resultIntent);
        sendBroadcastToIndex();
        if (type == TYPE_SELECT) {
            finish();
        }
    }

    private void sendBroadcastToIndex() {
//        sendBroadcast(new Intent(IndexActivity.UPDATE_CURRENT_CITY));
    }

    @Override
    protected void onDestroy() {
        if (mLocationManagerProxy != null) {
            mLocationManagerProxy.destroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.select_alphabetButton:
                float alphabetHeight = select_alphabetButton.getHeight();
                float y = event.getY();
                int sectionPosition = (int) (y / (alphabetHeight / (alphabet.size() + 1)));
                if (sectionPosition < 0) {
                    sectionPosition = 0;
                } else if (sectionPosition >= alphabet.size()) {
                    sectionPosition = alphabet.size() - 1;
                }
                String sectionLetter = alphabet.get(sectionPosition);
                select_alphabetButton.setCurrentItem(sectionPosition);
                int position = adapter.getPositionForSection(sectionPosition);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        select_alphabetButton.setBackgroundColor(Color.parseColor("#CCCCCC"));
                        select_toast_layout.setVisibility(View.VISIBLE);
                        section_toast_text.setText(sectionLetter);
                        lvSelectList.setSelection(position);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        lvSelectList.setSelection(position);
                        section_toast_text.setText(sectionLetter);
                        break;
                    default:
                        v.performClick();
                        select_toast_layout.setVisibility(View.GONE);
                        select_alphabetButton.setBackgroundColor(Color.parseColor("#F2F2F2"));
                        break;
                }
        }
        return false;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getAMapException().getErrorCode() == 0) {
            //获取位置信息
            tvPlace.setText("当前城市：" + aMapLocation.getCity());
            saveCountry(aMapLocation.getAdCode(), TYPE_LOCATION);
        } else {
            tvPlace.setText("定位失败，点击重试");
        }
        mLocationManagerProxy.destroy();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
