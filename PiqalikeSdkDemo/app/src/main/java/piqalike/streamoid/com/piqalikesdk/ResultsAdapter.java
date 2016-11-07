package piqalike.streamoid.com.piqalikesdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.streamoid.sdk.piqalike.models.Data;

import java.text.DecimalFormat;


/**
 * Created by vignesh7mailgmailcom on 10/20/16.
 */

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.Holder> {

    private static final StrikethroughSpan STRIKE_THROUGH_SPAN = new StrikethroughSpan();

    private final Context context;
    private final Data[] matches;

    public ResultsAdapter(Context context, Data[] matches) {
    this.context=context;
    this.matches=matches;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlay, parent, false);
        return new Holder(inflatedView);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Picasso.with(context).load(matches[position].getImage_urls()[0]).into(holder.mItemImage);
        holder.mItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(matches[position].getProduct_url()));
                context.startActivity(i);
            }
        });
        holder.title.setText(matches[position].getTitle());
        holder.brand.setText(matches[position].getBrand());
        float bprice=Float.parseFloat(matches[position].getPrice());
        float aprice=Float.parseFloat(matches[position].getDiscounted_price());
        float discount = (float) roundTwoDecimals(((bprice-aprice)/bprice)*100);
        if(matches[position].getDiscounted_price().equals(matches[position].getPrice())){
            holder.price.setText("\u20B9"+matches[position].getPrice());
        }else {
            String price = "\u20B9"+matches[position].getPrice() + "\n" + "\u20B9"+matches[position].getDiscounted_price();
            holder.price.setText(price, TextView.BufferType.SPANNABLE);
            Spannable spannable = (Spannable)  holder.price.getText();
            spannable.setSpan(STRIKE_THROUGH_SPAN, 0, "\u20B9".length()+matches[position].getPrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if(discount<0.1) {
            holder.discount.setVisibility(View.INVISIBLE);
        }else {
            holder.discount.setText(String.valueOf(discount)+"% OFF");
        }


    }

    @Override
    public int getItemCount() {
        return matches.length;
    }
    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
    public class Holder extends RecyclerView.ViewHolder {
        private final ImageView mItemImage;
        private final TextView title;
        private final TextView brand;
        private final TextView discount;
        private final TextView price;

        public Holder(View itemView) {
            super(itemView);
            mItemImage = (ImageView) itemView.findViewById(R.id.imageView5);
            title = (TextView) itemView.findViewById(R.id.textView3);
            brand = (TextView) itemView.findViewById(R.id.textView4);
            discount = (TextView) itemView.findViewById(R.id.textView6);
            price = (TextView) itemView.findViewById(R.id.textView5);
        }
    }
}
