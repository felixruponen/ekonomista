package com.example.uppgift_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class LoadThumbnailsAsync extends AsyncTask<ArrayList<String>, Integer, String> {

	private static final int THUMBNAIL_SIZE = 256;
	Context context;
	ProgressDialog pd;
	
	public LoadThumbnailsAsync(Context context){
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		pd = new ProgressDialog(context);
		pd.setTitle("Loading bitmaps..");
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.show();
		
	}
	
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		pd.dismiss();
	}


	@Override
	protected void onProgressUpdate(Integer... values) {

		pd.setProgress(values[0]);
	}

	@Override
	protected String doInBackground(ArrayList<String>... params) {
		
		if(params != null){
		
		ArrayList<String> images = params[0];
		

		
		if(images.size() != 0){		
			
			pd.setMax(images.size());
			
			for(int i = 0; i < images.size(); i++){
				
				Log.d("IMAGE: ", images.get(i));
				
				String[] temp = images.get(i).split("\\.");
				
				String thumbPath = temp[0] + "_thumb" + "." + temp[1];
				
				File thumbFile = new File(thumbPath);
				
				if(!thumbFile.exists()){
					
					File file = new File(images.get(i));
					
					Bitmap thumb = getPreview(file.toURI());
					
					try {
						thumbFile.createNewFile();
						
						FileOutputStream fos = new FileOutputStream(thumbFile);
						
						thumb.compress(Bitmap.CompressFormat.JPEG, 90, fos);
						fos.close();
						
					} catch (IOException e) {
	
						e.printStackTrace();
					}
					
				}		
				publishProgress(i);
				
			}
		}
		}
		
		return "";
	}
	
	Bitmap getPreview(URI uri) {
	    File image = new File(uri);

	    BitmapFactory.Options bounds = new BitmapFactory.Options();
	    bounds.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(image.getPath(), bounds);
	    if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
	        return null;

	    int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
	            : bounds.outWidth;

	    BitmapFactory.Options opts = new BitmapFactory.Options();
	    opts.inSampleSize = originalSize / THUMBNAIL_SIZE;
	    return BitmapFactory.decodeFile(image.getPath(), opts);     
	}

}
