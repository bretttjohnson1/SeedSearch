package com.seed.search.seed;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class SummaryActivity extends Activity implements View.OnClickListener {

    Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Bundle datum = getIntent().getExtras();
        data =datum.getBundle("Datum");
        TextView summary = (TextView)findViewById(R.id.summary_text);
        summary.setMovementMethod(new ScrollingMovementMethod());
        TextView page = (TextView) findViewById(R.id.page_text);
        summary.setText(data.getString("paragraph"));
        page.append(" "+data.getInt("page"));
        Button button = (Button)findViewById(R.id.jump_too);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        File file = new File(data.getString("filedirectory"));
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Instruct the user to install a PDF reader here, or something
        }


    }


}
