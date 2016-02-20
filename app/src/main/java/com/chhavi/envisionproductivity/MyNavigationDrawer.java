package com.chhavi.envisionproductivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

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
    public List<KeyPair> fitness ;
    @Override
    public void init(Bundle savedInstanceState) {

        account = new MaterialAccount(this.getResources(), null,null,null,R.drawable.productivity_secrets);

        this.addAccount(account);
        fitness = new ArrayList<KeyPair>();
        fitness = (ArrayList<KeyPair>)getIntent().getSerializableExtra("Fitness");
        Bundle bundle = new Bundle();
        bundle.putSerializable("Fitness", (ArrayList<KeyPair>)fitness);

        //ManifestoFragment sampleClassFragment = new ManifestoFragment();
        DashboardFragment dashboardFragment = new DashboardFragment();
        dashboardFragment.setArguments(bundle);

        ChartProductivityFragment chartProductivityFragment = new ChartProductivityFragment();
        AboutUsFragment aboutUsFragment = new AboutUsFragment();

        this.addSection(newSection("Dashboard",R.drawable.dashboardicon , dashboardFragment));
        this.addSection(newSection("Chart Productivity", R.drawable.chartsicon, chartProductivityFragment));
        this.addSection(newSection("About Us", R.drawable.info, aboutUsFragment));
    }
}