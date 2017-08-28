package com.example.toutiao;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.toutiao.module.base.BaseActivity;
import com.example.toutiao.module.media.channel.MediaChannelView;
import com.example.toutiao.module.news.NewsTabLayout;
import com.example.toutiao.module.photo.PhotoTabLayout;
import com.example.toutiao.module.video.VideoTabLayout;
import com.example.toutiao.util.L;
import com.example.toutiao.widget.helper.BottomNavigationViewHelper;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String thiz = MainActivity.class.getSimpleName();

    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PHOTO = 1;
    private static final int FRAGMENT_VIDEO = 2;
    private static final int FRAGMENT_MEDIA = 3;

    private int position;

    private Toolbar toolbar;
    private BottomNavigationView bottom_navigation;
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;

    private NewsTabLayout newsTabLayout;
    private PhotoTabLayout photoTabLayout;
    private VideoTabLayout videoTabLayout;
    private MediaChannelView mediaChannelView;

    private long exitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showFragment(FRAGMENT_NEWS);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        L.d(thiz, "toolbar: " + toolbar);
        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottom_navigation);
        setSupportActionBar(toolbar);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_news:
                        showFragment(FRAGMENT_NEWS);
                        break;
                    case R.id.action_photo:
                        showFragment(FRAGMENT_PHOTO);
                        break;
                    case R.id.action_video:
                        showFragment(FRAGMENT_VIDEO);
                        break;
                    case R.id.action_media:
                        showFragment(FRAGMENT_MEDIA);
                        break;
                }
                return true;
            }
        });

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);

    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        position = index;
        switch (index) {
            case FRAGMENT_NEWS:
                toolbar.setTitle(R.string.app_name);
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (newsTabLayout == null) {
                    newsTabLayout = NewsTabLayout.getInstance();
                    ft.add(R.id.container, newsTabLayout, NewsTabLayout.class.getName());
                } else {
                    ft.show(newsTabLayout);
                }
                break;
            case FRAGMENT_PHOTO:
                toolbar.setTitle(R.string.title_photo);
                if (photoTabLayout == null) {
                    photoTabLayout = PhotoTabLayout.getInstance();
                    ft.add(R.id.container, photoTabLayout, PhotoTabLayout.class.getName());
                } else {
                    ft.show(photoTabLayout);
                }
                break;
            case FRAGMENT_VIDEO:
                toolbar.setTitle(R.string.title_video);
                if (videoTabLayout == null) {
                    videoTabLayout = VideoTabLayout.getInstance();
                    ft.add(R.id.container, videoTabLayout, VideoTabLayout.class.getName());
                } else {
                    ft.show(videoTabLayout);
                }
                break;
            case FRAGMENT_MEDIA:
                toolbar.setTitle(R.string.title_media);
                if (mediaChannelView == null) {
                    mediaChannelView = MediaChannelView.getInstance();
                    ft.add(R.id.container, mediaChannelView, MediaChannelView.class.getName());
                } else {
                    ft.show(mediaChannelView);
                }
                break;
        }
        ft.commit();

        L.d(thiz, "showFragment() commit index" + index);

    }

    private void hideFragment(FragmentTransaction ft) {
        //如果不为空，就先隐藏起来
        if(newsTabLayout != null) {
            ft.hide(newsTabLayout);
        }
        if(photoTabLayout != null) {
            ft.hide(photoTabLayout);
        }
        if(videoTabLayout != null) {
            ft.hide(videoTabLayout);
        }
        if(mediaChannelView != null) {
            ft.hide(mediaChannelView);
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_switch_night_mode:

                return false;

            case R.id.nav_setting:

                return false;

            case R.id.nav_share:

                return false;
        }
        return false;
    }
}
