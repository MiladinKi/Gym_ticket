package gym_tickets.entities.dtos;

import java.util.Map;

public class WorkerEarningsDTO {

    private Map<String, Double> earningsByWorker;
    private Double totalEarnings;
    private Map<String, Long> ticketSoldByWorker;
    private Long totalTicketsSold;

    public WorkerEarningsDTO() {
    }

    public WorkerEarningsDTO(Map<String, Double> earningsByWorker, Double totalEarnings, Map<String, Long> ticketSoldByWorker, Long totalTicketsSold) {
        this.earningsByWorker = earningsByWorker;
        this.totalEarnings = totalEarnings;
        this.ticketSoldByWorker = ticketSoldByWorker;
        this.totalTicketsSold = totalTicketsSold;
    }

    public Map<String, Double> getEarningsByWorker() {
        return earningsByWorker;
    }

    public void setEarningsByWorker(Map<String, Double> earningsByWorker) {
        this.earningsByWorker = earningsByWorker;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public Map<String, Long> getTicketSoldByWorker() {
        return ticketSoldByWorker;
    }

    public void setTicketSoldByWorker(Map<String, Long> ticketSoldByWorker) {
        this.ticketSoldByWorker = ticketSoldByWorker;
    }

    public Long getTotalTicketsSold() {
        return totalTicketsSold;
    }

    public void setTotalTicketsSold(Long totalTicketsSold) {
        this.totalTicketsSold = totalTicketsSold;
    }
}
