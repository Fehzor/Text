package mechanics;

import models.textModel;
import java.util.*;
import java.awt.*;

public class item{
	public String name="";
	public String description="";
	public textModel icon;	

	//WILL NEED TO BE LOADED FROM A FILE!
	public item(String name,String description, textModel icon){
		this.name = name;
		this.description = description;
		this.icon = icon;
	}

}
