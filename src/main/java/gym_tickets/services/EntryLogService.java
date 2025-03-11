package gym_tickets.services;

import gym_tickets.entities.EntryLogEntity;
import gym_tickets.entities.TicketEntity;
import gym_tickets.entities.UserEntity;
import gym_tickets.entities.dtos.EntryCountDTO;
import gym_tickets.entities.dtos.EntryLogDTO;
import gym_tickets.mappers.EntryLogMapper;
import gym_tickets.repositories.EntryLogRepository;
import gym_tickets.repositories.TicketRepository;
import gym_tickets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntryLogService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntryLogRepository entryLogRepository;

    public EntryLogDTO registerEntry(Integer ticketId, Integer userId){
        TicketEntity ticket = ticketRepository.findById(ticketId).
                orElseThrow( ()-> new IllegalArgumentException("Ticket not found"));

        UserEntity user = userRepository.findById(userId).
                orElseThrow(()-> new IllegalArgumentException("User not found!"));

        Optional<EntryLogEntity> lastEntry = entryLogRepository.findTopByTicketOrderByEntryTimeDesc(ticket);
        if(lastEntry.isPresent() && lastEntry.get().getExitTime() == null) {
            throw new IllegalArgumentException("User is already inside gym!");
        }
            EntryLogEntity entryLog = new EntryLogEntity();
            entryLog.setTicket(ticket);
            entryLog.setUser(user);
            entryLog.setEntryTime(LocalDateTime.now());

            EntryLogEntity savedLog = entryLogRepository.save(entryLog);
            return EntryLogMapper.toDTO(savedLog);

        }

        public EntryLogDTO registerExit (Integer ticketId){

        TicketEntity ticket = ticketRepository.findById(ticketId).
                orElseThrow(()-> new IllegalArgumentException("Ticket not found!"));

        EntryLogEntity entryLog = entryLogRepository.findTopByTicketOrderByEntryTimeDesc(ticket).
                orElseThrow(()-> new IllegalStateException("No active visit found!"));

        if(entryLog.getExitTime() != null){
            throw new IllegalStateException("User has already exited");
        }
        entryLog.setExitTime(LocalDateTime.now());
        EntryLogEntity savedLog = entryLogRepository.save(entryLog);
        return EntryLogMapper.toDTO(savedLog);
        }

        public long getNumberOfEntriesBetween(LocalDateTime startTime, LocalDateTime endTime){
        return entryLogRepository.countByEntryTimeBetween(startTime, endTime);
        }

        public long getNumberOfExitsBetween(LocalDateTime startTime, LocalDateTime endTime){
        return entryLogRepository.countByExitTimeBetween(startTime, endTime);
        }

    public EntryCountDTO getDateWithMostEntries() {
        List<Object[]> results = entryLogRepository.findDateWithMostEntries();
        if (results.isEmpty()) {
            return null;
        }
        LocalDate date = ((java.sql.Date) results.get(0)[0]).toLocalDate();
        Long count = (Long) results.get(0)[1];

        return new EntryCountDTO(date, count);
    }

    public EntryCountDTO getDateWithTheLeastEntries(){

        List<Object[]> results = entryLogRepository.findDateWithLeastEntries();
        if (results.isEmpty()) {
            return null;
        }
        LocalDate date = ((java.sql.Date) results.get(0)[0]).toLocalDate();
        Long count = (Long) results.get(0)[1];

        return new EntryCountDTO(date, count);

    }



}
