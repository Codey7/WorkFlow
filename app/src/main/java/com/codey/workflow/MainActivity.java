package com.codey.workflow;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codey.fragment.AnnoFrag;
import com.codey.fragment.MeFrag;
import com.codey.fragment.WorkFlowFrag;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;


public class MainActivity extends FragmentActivity implements View.OnClickListener
{
    private AnnoFrag annoFrag;
    private MeFrag meFrag;
    private WorkFlowFrag workFlowFrag;

    private View annoFragLayout, meFragLayout, workFlowFragLayout;
    private ImageView annoImg, meImg, workImg;
    private TextView annoText, meText, workText;
    // 管理fragment
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView();

        //友盟推送
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.enable();
        String device_token = UmengRegistrar.getRegistrationId(this);
        Log.i("device_token",device_token);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        setTabSelection(0);
    }

    private void initView()
    {
        workFlowFragLayout = findViewById(R.id.layout_workflow);
        meFragLayout = findViewById(R.id.layout_me);
        annoFragLayout = findViewById(R.id.layout_anno);

        workImg = (ImageView) findViewById(R.id.iv_workflow);
        annoImg = (ImageView) findViewById(R.id.iv_anno);
        meImg = (ImageView) findViewById(R.id.iv_me);

        workText = (TextView) findViewById(R.id.tv_workflow);
        annoText = (TextView) findViewById(R.id.tv_anno);
        meText = (TextView) findViewById(R.id.tv_me);

        workFlowFragLayout.setOnClickListener(this);
        annoFragLayout.setOnClickListener(this);
        meFragLayout.setOnClickListener(this);
    }
    private void setTabSelection(int index)
    {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index)
        {
            case 0:
                workImg.setImageResource(R.drawable.ic_dashboard_black_18dp);
                workText.setTextColor(Color.BLACK);
                if(workFlowFrag==null)
                {
                    workFlowFrag=new WorkFlowFrag();
                    transaction.add(R.id.content,workFlowFrag);
                }
                else
                {
                    transaction.show(workFlowFrag);
                }
                break;
            case 1:
                annoImg.setImageResource(R.drawable.ic_list_black_18dp);
                annoText.setTextColor(Color.BLACK);
                if(annoFrag==null)
                {
                    annoFrag=new AnnoFrag();
                    transaction.add(R.id.content, annoFrag);
                }
                else
                {
                    transaction.show(annoFrag);
                }
                break;
            case 2:
                meImg.setImageResource(R.drawable.ic_perm_identity_black_18dp);
                meText.setTextColor(Color.BLACK);
                if(meFrag==null)
                {
                    meFrag=new MeFrag();
                    transaction.add(R.id.content, meFrag);
                }
                else
                {
                    transaction.show(meFrag);
                }
                break;
            default:
                    break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction fragmentTransaction)
    {
        if(workFlowFrag!=null)
            fragmentTransaction.hide(workFlowFrag);
        if (annoFrag!=null)
            fragmentTransaction.hide(annoFrag);
        if (meFrag!=null)
            fragmentTransaction.hide(meFrag);
    }
    private void clearSelection()
    {
        workImg.setImageResource(R.drawable.ic_dashboard_white_18dp);
        annoImg.setImageResource(R.drawable.ic_list_white_18dp);
        meImg.setImageResource(R.drawable.ic_perm_identity_white_18dp);

        workText.setTextColor(Color.WHITE);
        annoText.setTextColor(Color.WHITE);
        meText.setTextColor(Color.WHITE);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.layout_workflow:
                setTabSelection(0);
                break;
            case R.id.layout_anno:
                setTabSelection(1);
                break;
            case R.id.layout_me:
                setTabSelection(2);
                break;
        }
    }

// 再按一次返回键退出
private long firstTime;

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onBackPressed()
     */
    @Override
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        if (System.currentTimeMillis() - firstTime < 2000)
        {
            finish();
        } else
        {
            firstTime = System.currentTimeMillis();

            Toast.makeText(this, R.string.press_again_backrun, Toast.LENGTH_SHORT).show();
            // T.showShort(this, R.string.press_again_backrun);
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
