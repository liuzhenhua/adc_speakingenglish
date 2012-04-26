package info.liuqy.adc.spken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SpeakingChineseActivity extends ListActivity {
	SharedPreferences preferences = null;
	SharedPreferences.Editor editor = null;
	Map<String, String> exprs = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speakingenglish);
        preferences = getSharedPreferences("speaking",MODE_PRIVATE);
        editor = preferences.edit();
        
		try {
			PaserXML paser = new PaserXML();
			exprs = paser.loadExpressionsFromXml(this, PaserXML.DFA.EN_TAG, R.xml.cn2en);
			refreshList();
		} catch (IOException e) {
			Toast.makeText(this, R.string.error_xml_file, Toast.LENGTH_SHORT);
		} catch (XmlPullParserException e) {
			Toast.makeText(this, R.string.error_parsing_xml, Toast.LENGTH_SHORT);
		}
    }
    @Override
    protected void onPause()
    {
    	super.onPause();
    	editor.clear();
    	editor.putString("language","chinese");
    	editor.commit();
    }
    
    private void refreshList() {
		List<String> cns = new ArrayList<String>();
		for (String cn : exprs.keySet()) {
			cns.add(cn);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, cns);
		this.setListAdapter(adapter);
    }
    
    /* (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		TextView tv = (TextView)v;
		String text = tv.getText().toString();
		if (exprs.containsKey(text))
			tv.setText(exprs.get(text));
		else
			refreshList();
	}
}