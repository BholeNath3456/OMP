package com.example.onlinemoneypay;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<WishlistModel> wishlistModelList;
    private Boolean wishlist;
     private int lastPosition=-1;
    public WishlistAdapter(List<WishlistModel> wishlistModelList,Boolean wishlist) {
        this.wishlistModelList = wishlistModelList;
        this.wishlist=wishlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wishlist_item_layout, viewGroup, false);


        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String resource = wishlistModelList.get(position).getProductImage();
        String title = wishlistModelList.get(position).getProductTitle();
        long freeCoupens = wishlistModelList.get(position).getFreeCoupens();
        String rating = wishlistModelList.get(position).getRating();
        long totalRatings  = wishlistModelList.get(position).getTotalRating();
        String productPrice=wishlistModelList.get(position).getProductPrice();
        String cuttedPrice=wishlistModelList.get(position).getCuttedPrice();
        boolean paymentMethhod=wishlistModelList.get(position).isCOD();
        String product_id=wishlistModelList.get(position).getProductID();
        viewHolder.setData(product_id,resource,title,freeCoupens,rating,totalRatings,productPrice,cuttedPrice,paymentMethhod);


        if(lastPosition<position){
            Animation animation= AnimationUtils.loadAnimation(viewHolder.itemView.getContext(),R.anim.fade_in);
            viewHolder.itemView.setAnimation(animation);
            lastPosition=position;
        }
    }

    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView freeCoupens;
        private ImageView coupenIcon;
        private TextView rating;
        private TextView totalRating;
        private View priceCut;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView paymentMethod;
        private ImageView deleteBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCoupens = itemView.findViewById(R.id.free_coupen);
            coupenIcon = itemView.findViewById(R.id.coupen_icon);
            rating = itemView.findViewById(R.id.tv_product_rating_miniview);
            totalRating = itemView.findViewById(R.id.total_ratings);
            priceCut = itemView.findViewById(R.id.price_cut);
            productPrice = itemView.findViewById(R.id.product_price);
            cuttedPrice = itemView.findViewById(R.id.cutted_price);
            paymentMethod = itemView.findViewById(R.id.payment_method);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
        }

        private void setData(String productid, String resource, String title, long freeCoupensNo, String averageRate, long totalRatingsNo, String price, String cuttedPriceValue, boolean COD) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.ic_home)).into(productImage);
            productTitle.setText(title);
            if (freeCoupensNo != 0) {
                coupenIcon.setVisibility(View.VISIBLE);
                if (freeCoupensNo == 1) {
                    freeCoupens.setText("free " + freeCoupensNo + " Coupen");
                } else {
                    freeCoupens.setText("free " + freeCoupensNo + " Coupens");
                }
            } else {
                coupenIcon.setVisibility(View.INVISIBLE);
                freeCoupens.setVisibility(View.INVISIBLE);
            }

            rating.setText(averageRate);
            totalRating.setText("("+totalRatingsNo+") ratings");
            productPrice.setText("Rs."+price+"/-");
            cuttedPrice.setText("Rs."+cuttedPriceValue+"/");
            if(COD){
                paymentMethod.setVisibility(View.VISIBLE);

            }
            else {
                paymentMethod.setVisibility(View.INVISIBLE);
            }
            if(wishlist){
                deleteBtn.setVisibility(View.VISIBLE);
            }
            else {
                deleteBtn.setVisibility(View.GONE);
            }
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailsActivity.ALREADY_ADDED_TO_WISHLIST=false;
                    DBqueries.removeWishlist ( itemView.getContext(),productid);
                    Toast.makeText(itemView.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetails=new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    productDetails.putExtra("PRODUCT_ID",productid);
                    itemView.getContext().startActivity(productDetails);
                }
            });
        }
    }
}
