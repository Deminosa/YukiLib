package de.deminosa.core.annotaion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:38:33 # 29.07.2021
*
*/

@Retention(RetentionPolicy.RUNTIME)
public @interface NotReady {

	public String description();
	public String version();
	public String author();
	
}
