/*
ToggleImageButton.java
Copyright (C) 2010  Belledonne Communications, Grenoble, France

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/
package org.linphone.component;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * Image button storing a checked state to display alternating drawables.
 * The "toggled" drawable is displayed when button is down / checked.
 * The "untoggled" drawable is displayed when button is up / unchecked.
 *
 * @author Guillaume Beraudo
 *
 */
public class ToggleImageButton extends ImageButton implements OnClickListener {
	private static final String namespace = null;
	private boolean checked;
	private Drawable on;
	private Drawable off;
	private OnCheckedChangeListener onCheckedChangeListener;

	public ToggleImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		on = getResources().getDrawable(attrs.getAttributeResourceValue(namespace, "toggled", -1));
		off = getResources().getDrawable(attrs.getAttributeResourceValue(namespace, "untoggled", -1));
		setBackgroundColor(Color.TRANSPARENT);

		setOnClickListener(this);
		handleCheckChanged();
	}



	public void setChecked(boolean checked) {
		this.checked = checked;
		handleCheckChanged();
	}
	
	public boolean isChecked() {
		return checked;
	}


	private void handleCheckChanged() {
		setImageDrawable(checked?on:off);
		requestLayout();
		invalidate();
		if (onCheckedChangeListener != null) onCheckedChangeListener.onCheckedChanged(this, checked);
	}
	
	
	public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
		onCheckedChangeListener = listener;
	}
	
	public static interface OnCheckedChangeListener {
		void onCheckedChanged(ToggleImageButton button, boolean checked);
	}

	public void onClick(View v) {
		checked = !checked;
		handleCheckChanged();
	}
}
