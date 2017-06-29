package freewilder.rockme.com.freewilder.adapters;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.Utils.AppLog;
import freewilder.rockme.com.freewilder.customlistview.StringMatcher;
import freewilder.rockme.com.freewilder.helper.CircleTransform;
import freewilder.rockme.com.freewilder.helper.TextDrawable;
import freewilder.rockme.com.freewilder.pojo.SetGetContact;

/**
 * Created by su on 6/28/17.
 */

public class AdapterContactList extends ArrayAdapter<SetGetContact> implements SectionIndexer {
    LayoutInflater Minflater;
    ArrayList<SetGetContact> LIST;
    ArrayList<SetGetContact> FilteredList;
    private CountryFilter filter;
    Context Ctx;
    private static String sections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public AdapterContactList(Context context, int resource, ArrayList<SetGetContact> setGetContactPhone) {
        super(context, resource);
        this.Ctx = context;
        this.LIST = setGetContactPhone;
        this.FilteredList = setGetContactPhone;
        Minflater = (LayoutInflater) Ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return LIST.size();
    }


    @Override
    public SetGetContact getItem(int position) {
        return LIST.get(position);
    }

    public long getItemId(int position) {
        return LIST.get(position).hashCode();
    }
    @Override
    public int getPositionForSection(int section) {
        // If there is no item for current section, previous section will be selected
        for (int i = section; i >= 0; i--) {
            for (int j = 0; j < getCount(); j++) {
                if (i == 0) {
                    // For numeric section
                    for (int k = 0; k <= 9; k++) {
                        if (StringMatcher.match(String.valueOf(getItem(j).getName().charAt(0)), String.valueOf(k)))
                            return j;
                    }
                } else {
                    if (StringMatcher.match(String.valueOf(getItem(j).getName().charAt(0)), String.valueOf(sections.charAt(i))))
                        return j;
                }
            }
        }
        return 0;
    }


    @Override
    public Object[] getSections() {
        AppLog.info("ListView", "Get sections");
        String[] sectionsArr = new String[sections.length()];
        for (int i=0; i < sections.length(); i++)
            sectionsArr[i] = "" + sections.charAt(i);

        return sectionsArr;

    }



    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    private class ViewHolder {
        RelativeLayout main;
        ImageView Phot_Tumb;
        TextView name;
        TextView emailis;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CountryFilter();
        }
        return filter;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        SetGetContact rowitem=getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = Minflater.inflate(R.layout.adapter_contcatlist, null);
            holder.main= (RelativeLayout) convertView.findViewById(R.id.main);
            holder.Phot_Tumb = (ImageView) convertView.findViewById(R.id.IMG_Thumb);
            holder.name = (TextView) convertView.findViewById(R.id.TXT_NAME);
            holder.emailis=(TextView)convertView.findViewById(R.id.emailis);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String NAme_First_Character=LIST.get(position).getName().substring(0,1).toUpperCase();
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .withBorder(4) /* thickness in px */
                .endConfig()
                .buildRound(NAme_First_Character, Color.parseColor("#FF00bf8f"));

        if(!LIST.get(position).isCheck()){
            Glide.with(Ctx).load(LIST.get(position).getPhotoUri()).placeholder(drawable).transform(new CircleTransform(Ctx)).into(holder.Phot_Tumb);
            holder.main.setBackgroundColor(ContextCompat.getColor(Ctx, R.color.colorWhite));
        }else {
            Glide.with(Ctx).load("").placeholder(R.drawable.ticked_circle).transform(new CircleTransform(Ctx)).into(holder.Phot_Tumb);
            holder.main.setBackgroundColor(ContextCompat.getColor(Ctx, R.color.colorGrayBackGround));
        }


        holder.name.setText(LIST.get(position).getName());
        holder.emailis.setText(LIST.get(position).getEmailID());


        holder.main.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (LIST.get(position).isCheck()) {
                    LIST.get(position).setCheck(false);
                    AppLog.info("setGetContactWithEmailID" + position, "" + LIST.get(position).isCheck());
                } else {
                    LIST.get(position).setCheck(true);
                    AppLog.info("setGetContactWithEmailID" + position, "" + LIST.get(position).isCheck());
                }

                AppLog.info("Long press","Long press");

                notifyDataSetChanged();

                return true;
            }
        });
        return convertView;
    }


    private class CountryFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (constraint != null && constraint.toString().length() > 0) {
                ArrayList<SetGetContact> filteredItems = new ArrayList<SetGetContact>();

                for (int i = 0, l = FilteredList.size(); i < l; i++) {
                    SetGetContact country = FilteredList.get(i);
                    if (country.getName().toLowerCase().contains(constraint))
                        filteredItems.add(country);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            } else {
                synchronized (this) {
                    result.values = FilteredList;
                    result.count = FilteredList.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            LIST = (ArrayList<SetGetContact>) results.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = LIST.size(); i < l; i++) {
                add(LIST.get(i));
            }
            notifyDataSetInvalidated();
        }
    }
}
