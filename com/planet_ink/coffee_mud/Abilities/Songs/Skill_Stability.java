package com.planet_ink.coffee_mud.Abilities.Songs;

import com.planet_ink.coffee_mud.interfaces.*;
import com.planet_ink.coffee_mud.Abilities.StdAbility;
import com.planet_ink.coffee_mud.common.*;
import com.planet_ink.coffee_mud.utils.*;
import java.util.*;

public class Skill_Stability extends BardSkill
{
	public String ID() { return "Skill_Stability"; }
	public String name(){return "Stability";}
	public String displayText(){return "";}
	public int quality(){ return INDIFFERENT;}
	protected int canAffectCode(){return CAN_MOBS;}
	public Environmental newInstance(){	return new Skill_Stability();}
	public boolean isAutoInvoked(){return true;}
	public boolean canBeUninvoked(){return false;}
	public int classificationCode(){return Ability.SKILL;}

	public boolean okMessage(Environmental myHost, CMMsg msg)
	{
		if(!super.okMessage(myHost,msg))
			return false;

		if((affected==null)||(!(affected instanceof MOB)))
			return true;

		MOB mob=(MOB)affected;
		if((msg.tool()!=null)
		&&(msg.tool() instanceof Ability)
		&&(msg.amITarget(affected))
		&&(((Ability)msg.tool()).quality()==Ability.MALICIOUS)
		&&(Util.bset(((Ability)msg.tool()).flags(),Ability.FLAG_MOVING))
		&&((mob.fetchAbility(ID())==null)||profficiencyCheck(null,-40,false)))
		{
			Room roomS=null;
			Room roomD=null;
			if((msg.target()!=null)&&(msg.target() instanceof MOB))
				roomD=((MOB)msg.target()).location();
			if((msg.source()!=null)&&(msg.source().location()!=null))
				roomS=msg.source().location();
			if((msg.target()!=null)&&(msg.target() instanceof Room))
				roomD=(Room)msg.target();

			if((roomS!=null)&&(roomD!=null)&&(roomS==roomD))
				roomD=null;

			if(roomS!=null)
				roomS.show((MOB)affected,null,msg.tool(),CMMsg.MSG_OK_VISUAL,"<S-NAME> remain(s) stable despite the <O-NAME>.");
			if(roomD!=null)
				roomS.show((MOB)affected,null,msg.tool(),CMMsg.MSG_OK_VISUAL,"<S-NAME> remain(s) stable despite the <O-NAME>.");
			helpProfficiency((MOB)affected);
			return false;
		}
		return true;
	}


}
