package com.example.hellogridview;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview= (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ImageAdapter extends BaseAdapter{
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext=c;
        }

        public int getCount(){
            return mThumbsIds.length;
        }

        public Object getItem(int position){
            return null;
        }

        public long getItemId(int position){
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            ImageView imageView;
            if (convertView == null){
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            }else {
                imageView=(ImageView) convertView;
            }

            imageView.setImageResource(mThumbsIds[position]);
            return imageView;
        }

        private Integer[] mThumbsIds = {
                R.drawable.img1, R.drawable.img2,
                R.drawable.img3, R.drawable.img4,
                R.drawable.img5, R.drawable.img6,
                R.drawable.img7, R.drawable.img8,
                R.drawable.img9, R.drawable.img10,
                R.drawable.img11, R.drawable.img12,
                R.drawable.img13, R.drawable.img14
        };
    }
}