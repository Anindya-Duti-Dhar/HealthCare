package anindya.duti.com.bmi.exercise.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import anindya.duti.com.bmi.R;
import anindya.duti.com.bmi.exercise.activity.ExerciseDetails;
import anindya.duti.com.bmi.exercise.model.Exercise1ListItem;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class Exercise1ListAdapter extends RecyclerView.Adapter<Exercise1ListAdapter.ViewHolder> {

    String mItemName, mItemHint;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView ListLogoImage;
        public TextView ListTitle, ListInstructionButtonText;
        public CardView wholeLayout;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            wholeLayout = (CardView) itemView.findViewById(R.id.card_item);
            ListTitle = (TextView) itemView.findViewById(R.id.exercise_1_list_item_title);
            ListInstructionButtonText = (TextView) itemView.findViewById(R.id.exercise_1_list_item_instruction);
            ListLogoImage = (ImageView) itemView.findViewById(R.id.exercise_1_list_item_logo_image);
        }

    }

    // Store a member variable for the contacts
    private ArrayList<Exercise1ListItem> _data;
    // Store the context for easy access
    private Context mContext;

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Pass in the contact array into the constructor
    public Exercise1ListAdapter(Context mContext, ArrayList<Exercise1ListItem> _data) {
        this._data = _data;

        _data.add(0, getListItem(mContext.getResources().getString(R.string.ab_crunches_title), R.drawable.abdominal_crunches, "abdominal_crunches"));
        _data.add(1, getListItem(mContext.getResources().getString(R.string.high_step_title), R.drawable.high_stepping, "high_stepping"));
        _data.add(2, getListItem(mContext.getResources().getString(R.string.jumping_title), R.drawable.jumping_jacks, "jumping_jacks"));
        _data.add(3, getListItem(mContext.getResources().getString(R.string.lunges_title), R.drawable.lunges, "lunges"));
        _data.add(4, getListItem(mContext.getResources().getString(R.string.plank_title), R.drawable.plank, "plank"));
        _data.add(5, getListItem(mContext.getResources().getString(R.string.push_up_title), R.drawable.push_ups, "push_up"));
        _data.add(6, getListItem(mContext.getResources().getString(R.string.push_rotate_title), R.drawable.push_up_and_rotation, "push_up_and_rotation"));
        _data.add(7, getListItem(mContext.getResources().getString(R.string.side_plank_title), R.drawable.side_plank, "side_plank"));
        _data.add(8, getListItem(mContext.getResources().getString(R.string.squats_title), R.drawable.squats, "squats"));
        _data.add(9, getListItem(mContext.getResources().getString(R.string.step_up_title), R.drawable.step_up_onto_chair, "step_up_onto_chair"));
        _data.add(10, getListItem(mContext.getResources().getString(R.string.triceps_title), R.drawable.triceps_dips_on_chair, "triceps_dips_on_chair"));
        _data.add(11, getListItem(mContext.getResources().getString(R.string.wall_sit_title), R.drawable.wall_sit, "wall_sit"));
    }

    public Exercise1ListItem getListItem(String title, int imageSource, String hint) {
        Exercise1ListItem StaticItem = new Exercise1ListItem();
        StaticItem.setTitle(title);
        StaticItem.setImageUrl(imageSource);
        StaticItem.setHint(hint);
        return StaticItem;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public Exercise1ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        // Inflate the custom layout
        View exerciseView = inflater.inflate(R.layout.exercise_1_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(exerciseView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(Exercise1ListAdapter.ViewHolder viewHolder, int position) {

        // Get the data model based on position
        final Exercise1ListItem data = _data.get(position);

       final Typeface font = Typeface.createFromAsset(mContext.getAssets(), "android.ttf");

        // Set item views based on your views and data model
        CardView wholeLayout = viewHolder.wholeLayout;

        TextView ListTitle = viewHolder.ListTitle;
        ListTitle.setText(data.getTitle());
        ListTitle.setTypeface(font);

        TextView ListInstructionButtonText = viewHolder.ListInstructionButtonText;
        ListInstructionButtonText.setText(mContext.getResources().getString(R.string.exerciseInstructionButton));
        ListInstructionButtonText.setTypeface(font);

        ImageView ListLogoImage = viewHolder.ListLogoImage;
        ListLogoImage.setImageResource(data.getImageUrl());

        // Set Instruction textview listener android
        viewHolder.wholeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemName = (data.getTitle());
                mItemHint = (data.getHint());
                Intent intent = new Intent(view.getContext(), ExerciseDetails.class);
                intent.putExtra("toolbar_item_name", mItemName);
                intent.putExtra("item_hint", mItemHint);
                view.getContext().startActivity(intent);
            }
        });

}

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return _data.size();
    }

}