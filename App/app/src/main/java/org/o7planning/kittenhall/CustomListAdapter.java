package org.o7planning.kittenhall;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.o7planning.kittenhall.bean.NFT;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<NFT> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private double val_eur;


    public CustomListAdapter(Context aContext,  List<NFT> listData, double val_eur) {
        this.context = aContext;
        this.listData = listData;
        this.val_eur = val_eur;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.liste_nft_layout, null);
            holder = new ViewHolder();
            holder.img_nft = (ImageView) convertView.findViewById(R.id.img_nft);
            holder.txt_nom = (TextView) convertView.findViewById(R.id.txt_nom);
            holder.txt_val = (TextView) convertView.findViewById(R.id.txt_val);
            holder.txt_percent = (TextView) convertView.findViewById(R.id.txt_percent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NFT nft = this.listData.get(position);
        holder.txt_nom.setText(nft.getNom());
        double prixEur = nft.getVal_eth()*val_eur;
        double prixEurRound = Math.round(prixEur * 100.0) / 100.0;
        holder.txt_val.setText(Double.toString(nft.getVal_eth()) + " ETH (" + prixEurRound + "€)");
        double percent = ((prixEurRound - nft.getVal_eur())/nft.getVal_eur())*100;
        //double percent = (nft.getVal_eur() - prixEur)/prixEur;
        double percentRound = Math.round(percent * 100.0) / 100.0;
        Log.i("Value eur :", Double.toString(nft.getVal_eur()));
        if(percentRound>0){
            holder.txt_percent.setText("+" + Double.toString(percentRound) + "%");
            holder.txt_percent.setTextColor(Color.GREEN);
        }else{
            holder.txt_percent.setText(Double.toString(percentRound) + "%");
            if(percentRound < 0.0){
                holder.txt_percent.setTextColor(Color.RED);
            }
        }


        int imageId = nft.getId_image();


        holder.img_nft.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView img_nft;
        TextView txt_nom;
        TextView txt_val;
        TextView txt_percent;
    }
}
