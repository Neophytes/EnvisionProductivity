package com.chhavi.envisionproductivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;

//public class MyNavigationDrawer extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_navigation_drawer);
//    }
//
//}
public class MyNavigationDrawer extends MaterialNavigationDrawer {

    MaterialAccount account;
    @Override
    public void init(Bundle savedInstanceState) {
        account = new MaterialAccount(this.getResources(), "User Name",null,null,R.drawable.productivity_secrets);
        this.addAccount(account);
        //ManifestoFragment sampleClassFragment = new ManifestoFragment();
        DashboardFragment dashboardFragment = new DashboardFragment();
        ChartProductivityFragment chartProductivityFragment = new ChartProductivityFragment();
        AboutUsFragment aboutUsFragment = new AboutUsFragment();

        this.addSection(newSection("Dashboard",R.mipmap.ic_launcher , dashboardFragment));
        this.addSection(newSection("Chart Productivity", R.mipmap.ic_launcher, chartProductivityFragment));
        this.addSection(newSection("About Us", R.mipmap.ic_launcher, aboutUsFragment));
    }
}