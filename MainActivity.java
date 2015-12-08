package com.hz.eight;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	/**
	 * 碎片界面管理器
	 */
	private FragmentManager fragmentManager;
	/**
	 * access布局视图
	 */
	private View accessView;
	/**
	 * wrench布局视图
	 */
	private View wrenchView;
	/**
	 * gear布局视图
	 */
	private View gearView;
	/**
	 * access 图标
	 */
	private ImageView accImageView;
	/**
	 * wrench图标
	 */
	private ImageView wreImageView;
	/**
	 * gear图标
	 */
	private ImageView gearImageView;
	/**
	 * access菜单文本
	 */
	private TextView accessTextView;
	/**
	 * wrench菜单文本
	 */
	private TextView wrenchTextView;
	/**
	 * gear菜单文本
	 */
	private TextView gearTextView;
	/**
	 * access界面
	 */
	private AccessFragment accessFragment;
	/**
	 * wrench界面
	 */
	private WrenchFragment wrenchFragment;
	/**
	 * gear界面
	 */
	private GearFragment gearFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		fragmentManager = getFragmentManager();
		selectTab(1);
	}

	/**
	 * 方法说明 初始化视图
	 * 
	 * @author HZ
	 * @since 2015-12-8
	 */
	private void initView() {
		accessView = findViewById(R.id.access_layout);
		wrenchView = findViewById(R.id.wrench_layout);
		gearView = findViewById(R.id.gear_layout);

		accImageView = (ImageView) findViewById(R.id.image_access);
		wreImageView = (ImageView) findViewById(R.id.image_wrench);
		gearImageView = (ImageView) findViewById(R.id.image_gear);

		accessTextView = (TextView) findViewById(R.id.tv_access);
		wrenchTextView = (TextView) findViewById(R.id.tv_wrench);
		gearTextView = (TextView) findViewById(R.id.tv_gear);
		/*监听这三个视图*/
		accessView.setOnClickListener(this);
		wrenchView.setOnClickListener(this);
		gearView.setOnClickListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.access_layout:
			selectTab(1);
			break;
		case R.id.wrench_layout:
			selectTab(2);
			break;
		case R.id.gear_layout:
			selectTab(3);
			break;
		default:
			break;
		}

	}

	/**
	 * 方法说明 选择 功能
	 * 
	 * @author HZ
	 * @since 2015-12-8
	 */
	private void selectTab(int index) {
		/*清除先前选中的状态*/
		clearSelect();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		/*防止多个界面重叠显示，先隐藏*/
		hideFragment(fragmentTransaction);

		/* select tab */
		switch (index) {
		case 1:
			accImageView.setImageResource(R.drawable.select_access);
			accessTextView.setTextColor(Color.GREEN);
			if (accessFragment == null) {
				accessFragment = new AccessFragment();
				fragmentTransaction.add(R.id.content, accessFragment);
			} else {
				fragmentTransaction.show(accessFragment);
			}
			break;
		case 2:
			wreImageView.setImageResource(R.drawable.select_wrench);
			wrenchTextView.setTextColor(Color.GREEN);
			if (wrenchFragment == null) {
				wrenchFragment = new WrenchFragment();
				fragmentTransaction.add(R.id.content, wrenchFragment);
			} else {
				fragmentTransaction.show(wrenchFragment);
			}
			break;
		case 3:
		default:
			gearImageView.setImageResource(R.drawable.select_gear);
			gearTextView.setTextColor(Color.GREEN);
			if (gearFragment == null) {
				gearFragment = new GearFragment();
				fragmentTransaction.add(R.id.content, gearFragment);
			} else {
				fragmentTransaction.show(gearFragment);
			}
			break;

		}

		fragmentTransaction.commit();

	}

	/**
	 * 方法说明 清除上次选择，即设置为默认的状态属性
	 *@author HZ
	 *@since 2015-12-8
	 */
	private void clearSelect() {
		accImageView.setImageResource(R.drawable.access);
		accessTextView.setTextColor(Color.GRAY);
		wreImageView.setImageResource(R.drawable.wrench);
		wrenchTextView.setTextColor(Color.GRAY);
		gearImageView.setImageResource(R.drawable.gear);
		gearTextView.setTextColor(Color.GRAY);
		
	}

	/**
	 * 方法说明 隐藏碎片界面
	 *@author HZ
	 *@since 2015-12-8
	 */
	private void hideFragment(FragmentTransaction fragmentTransaction) {
		
		if (accessFragment != null) {
			fragmentTransaction.hide(accessFragment);
		}
		if (gearFragment != null) {
			fragmentTransaction.hide(gearFragment);
		}
		if (wrenchFragment != null) {
			fragmentTransaction.hide(wrenchFragment);
		}
		
	}

}
