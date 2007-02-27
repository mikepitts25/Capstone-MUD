package com.planet_ink.coffee_mud.Abilities.Prayers;
import com.planet_ink.coffee_mud.core.interfaces.*;
import com.planet_ink.coffee_mud.core.*;
import com.planet_ink.coffee_mud.Abilities.interfaces.*;
import com.planet_ink.coffee_mud.Areas.interfaces.*;
import com.planet_ink.coffee_mud.Behaviors.interfaces.*;
import com.planet_ink.coffee_mud.CharClasses.interfaces.*;
import com.planet_ink.coffee_mud.Commands.interfaces.*;
import com.planet_ink.coffee_mud.Common.interfaces.*;
import com.planet_ink.coffee_mud.Exits.interfaces.*;
import com.planet_ink.coffee_mud.Items.interfaces.*;
import com.planet_ink.coffee_mud.Locales.interfaces.*;
import com.planet_ink.coffee_mud.MOBS.interfaces.*;
import com.planet_ink.coffee_mud.Races.interfaces.*;


import java.util.*;

/* 
   Copyright 2000-2007 Bo Zimmerman

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

public class Prayer_MinorInfusion extends Prayer implements MendingSkill
{
    public String ID() { return "Prayer_MinorInfusion"; }
    public String name(){ return "Minor Infusion";}
	public int classificationCode(){return Ability.ACODE_PRAYER|Ability.DOMAIN_HEALING;}
    public int abstractQuality(){ return  Ability.QUALITY_BENEFICIAL_OTHERS;}
    public long flags(){return Ability.FLAG_HOLY|Ability.FLAG_UNHOLY;}
    protected long minCastWaitTime(){return Tickable.TIME_TICK/2;}

	public boolean supportsMending(Environmental E)
	{ 
		return (E instanceof MOB)
				&&((((MOB)E).curState()).getMana()<(((MOB)E).maxState()).getMana());
	}
	
    public boolean invoke(MOB mob, Vector commands, Environmental givenTarget, boolean auto, int asLevel)
    {
        MOB target=this.getTarget(mob,commands,givenTarget);
        if(target==null) return false;

        if(!super.invoke(mob,commands,givenTarget,auto,asLevel))
            return false;

        boolean success=proficiencyCheck(mob,0,auto);

        if(success)
        {
            // it worked, so build a copy of this ability,
            // and add it to the affects list of the
            // affected MOB.  Then tell everyone else
            // what happened.
            CMMsg msg=CMClass.getMsg(mob,target,this,verbalCastCode(mob,target,auto),auto?"A soft yellow glow surrounds <T-NAME>.":"^S<S-NAME> "+prayWord(mob)+", delivering a light touch of infusion to <T-NAMESELF>.^?");
            if(mob.location().okMessage(mob,msg))
            {
                mob.location().send(mob,msg);
                int healing=CMLib.dice().roll(2,adjustedLevel(mob,asLevel),10);
                target.curState().adjMana(healing,target.maxState());
                target.tell("You feel slightly restored!");
                lastCastHelp=System.currentTimeMillis();
            }
        }
        else
            beneficialWordsFizzle(mob,target,auto?"":"<S-NAME> "+prayWord(mob)+" for <T-NAMESELF>, but nothing happens.");
        // return whether it worked
        return success;
    }
}
