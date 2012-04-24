package info.liuqy.adc.spken;

import java.util.Locale;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity
{
	SharedPreferences preferences = null;
	SharedPreferences.Editor editor = null;
	private TabHost tabHost = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		preferences = getSharedPreferences("speaking",MODE_PRIVATE);
        editor = preferences.edit();
        
        String language = preferences.getString("language",null);

		// 获得TabHost对象
		tabHost = getTabHost();
		// 创建TabSpec标签
		TabHost.TabSpec englishSpec = getTabSpec(
				SpeakingEnglishActivity.class, "说英语",
				android.R.drawable.btn_star_big_on);
		TabHost.TabSpec chineseSpec = getTabSpec(
				SpeakingChineseActivity.class, "Speaking Chinese",
				android.R.drawable.btn_star_big_on);
		// 把TabSpec添加到TabHost中
		tabHost.addTab(englishSpec);
		tabHost.addTab(chineseSpec);
		
		if("english".equals(language))
		{
			tabHost.setCurrentTab(0);
		}
		else if("chinese".equals(language))
		{
			tabHost.setCurrentTab(1);
		}
		else 
		{
			String lang = Locale.getDefault().getLanguage();
			if(Locale.CHINESE.equals(lang))
			{
				tabHost.setCurrentTab(0);
			}
			else if(Locale.ENGLISH.equals(lang))
			{
				tabHost.setCurrentTab(1);
			}
			
		}
		
		

	}

	private TabHost.TabSpec getTabSpec(Class<?> cls,
			String tagName, int icon)
	{
		// 创建一个Intent，用来设置TabSpec内容的
		Intent intent = new Intent(this, cls);
		// 创建一个TabSpec并设计标签
		TabHost.TabSpec spec = tabHost.newTabSpec(tagName);
		// 设置TabSpec显示内容
		spec.setContent(intent);
		// 设置TabSpec 的标签名与图标
		spec.setIndicator(tagName, getResources()
				.getDrawable(icon));

		return spec;
	}

}

