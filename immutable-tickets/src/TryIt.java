import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demo that shows how the immutable design works.
 *
 * - No setters => can't mutate after creation
 * - Tags list is unmodifiable => external add throws exception
 * - "Updates" return a new ticket, original stays unchanged
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1) create a ticket through the service
        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // 2) "assign" returns a NEW ticket; original t is untouched
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        System.out.println("\nAfter assign (new ticket): " + assigned);
        System.out.println("Original unchanged:        " + t);

        // 3) escalate also returns a new ticket
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nAfter escalation (new ticket): " + escalated);
        System.out.println("Assigned still unchanged:      " + assigned);

        // 4) try to mutate the tags list from outside — this should throw
        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nOops, tags were mutated!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nExternal tag mutation blocked (UnsupportedOperationException).");
        }

        // 5) show fluent builder usage directly
        IncidentTicket custom = new IncidentTicket.Builder("INC-42", "user@scaler.com", "Wifi not working")
                .priority("HIGH")
                .description("Floor 3, room 305")
                .addTag("INFRA")
                .addTag("URGENT")
                .slaMinutes(60)
                .source("EMAIL")
                .customerVisible(true)
                .build();
        System.out.println("\nCustom ticket: " + custom);
    }
}
