package org.wildstang.framework.logger;

import java.util.ArrayList;
import java.util.Date;

public class StateGroup
{
   
   private Date m_timestamp;
   private ArrayList<StateInfo> m_stateList = new ArrayList<StateInfo>();
   
   public StateGroup(Date p_timestamp)
   {
      m_timestamp = p_timestamp;
   }
   
   public void addState(String p_name, String p_parent, Object p_value)
   {
      StateInfo state = new StateInfo(p_name, p_parent, p_value);
      m_stateList.add(state);
   }

   public Date getTimestamp()
   {
      return m_timestamp;
   }

   public ArrayList<StateInfo> getStateList()
   {
      return m_stateList;
   }
   
   
   
}
