package com.oscar.galeriawhats.UI.Adapters;

import com.oscar.galeriawhats.UI.CallBacks.ItemClickListener;

/**
 * Created by oemy9 on 02/09/2017.
 */

public interface IAdapterBase {
    void setItemClickListener(ItemClickListener itemClickListener);
    Object getItem(int position);
}
