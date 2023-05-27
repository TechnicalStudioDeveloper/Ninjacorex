package ninja.corex.viewbind.drawerbing;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

public class DrawerCompat extends DrawerLayout{
	private Rect rect = new Rect();
	public DrawerCompat(Context context){
		super(context);
	}
	public DrawerCompat(@Nullable Context context,AttributeSet set){
		super(context,set);
	}
	
	public DrawerCompat(Context context,AttributeSet set,int defLine){
		super(context,set,defLine);
	}
	 	
	private View findScrollingChild(ViewGroup parent, float x, float y) {
		int n = parent.getChildCount();
		
		if (parent == this && n <= 1) {
			
			return null;
		}
		int start = 0;
		if (parent == this) {
			
			start = 1;
		}
		
		for (int i = start; i < n; i++) {
			
			View child = parent.getChildAt(i);
			
			if (child.getVisibility() != View.VISIBLE) {
				
				continue;
				
			}
			child.getHitRect(rect);
			
			if (rect.contains((int) x, (int) y)) {
				
				if (child.canScrollHorizontally(1)) {
					
					return child;
					
				} else if (child instanceof ViewGroup) {
					
					View v = findScrollingChild((ViewGroup) child, x - rect.left, y - rect.top);
					
					if (v != null) {
						
						return v;
						
					}
					
				}
				
			}
			
		}
		return null;
	}
}