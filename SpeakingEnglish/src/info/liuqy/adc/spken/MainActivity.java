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

		// ���TabHost����
		tabHost = getTabHost();
		// ����TabSpec��ǩ
		TabHost.TabSpec englishSpec = getTabSpec(
				SpeakingEnglishActivity.class, "˵Ӣ��",
				android.R.drawable.btn_star_big_on);
		TabHost.TabSpec chineseSpec = getTabSpec(
				SpeakingChineseActivity.class, "Speaking Chinese",
				android.R.drawable.btn_star_big_on);
		// ��TabSpec��ӵ�TabHost��
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
		// ����һ��Intent����������TabSpec���ݵ�
		Intent intent = new Intent(this, cls);
		// ����һ��TabSpec����Ʊ�ǩ
		TabHost.TabSpec spec = tabHost.newTabSpec(tagName);
		// ����TabSpec��ʾ����
		spec.setContent(intent);
		// ����TabSpec �ı�ǩ����ͼ��
		spec.setIndicator(tagName, getResources()
				.getDrawable(icon));

		return spec;
	}

}

