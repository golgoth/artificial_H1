/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent.profiler;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.states.MsgReceiver;
import java.util.Arrays;

/**
 *
 * @author Nabil
 */
class ReceiveInfoArtefact extends OneShotBehaviour{
    
    private ProfilerAgent profileragent;
    public ReceiveInfoArtefact(ProfilerAgent profileragent) {
        this.profileragent = profileragent;
    }
    
    @Override
    public void action() {
            
        //System.out.println("<" + myAgent.getLocalName() + ">: HANDLE MESSAGE");
        
        ACLMessage msg = myAgent.blockingReceive(MessageTemplate.MatchSender(new AID("curator", AID.ISLOCALNAME)));
        
        if (msg != null) {
            System.out.println("<" + myAgent.getLocalName() + ">: Message received,  with informations about the artwork");
        
            String received = msg.getContent();
            
            int entry=0;
            
            for(String s : Arrays.asList(received.split("/")))
            {
                    //System.out.println(s);
                    switch(entry){
                        case 0:
                            System.out.println("<" + myAgent.getLocalName() + ">: **** ArtWork name is : "+s);
                            break;
                        case 1 : 
                            System.out.println("<" + myAgent.getLocalName() + ">: **** The creator is : "+s);
                            break;
                        case 2 : 
                            System.out.println("<" + myAgent.getLocalName() + ">: **** Description : "+s);
                            break;
                        default : 
                            System.out.println("<" + myAgent.getLocalName() + ">: ENTRY NUMBER ERROR");
                            break;
                    }
                    entry++;
            }
        }
        else {
             System.out.println("<" + myAgent.getLocalName() + ">: Message NULL");
            block();
        }
        
        
    }
    
    
    
}
