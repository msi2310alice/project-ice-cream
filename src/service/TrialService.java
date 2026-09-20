package service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import exception.validation.InvalidDataException;
import exception.validation.DuplicateIdException;
import exception.validation.InvalidSearchDataException;

import util.Validator;

import model.trial.Trial;
import sorting.IFSortingStrategy;
import type.SortDirection;

import repository.trial.IFTrialRepository;
import exception.file.FileDataException;
import model.formula.Formula;;

public class TrialService {
    private List<Trial> trials;
    private Map<String, Trial> trialMap;

    private final IFTrialRepository trialRepository;

    private Queue<Trial> trialsWaiting; //các trial đang chờ đánh giá cảm quan (dựa vào số lượng phần tử trong danh sách đánh giá cảm quan của thử nghiệm)
    private Deque<Trial> trialHistory; //lịch sử các trial được thêm

    public TrialService(IFTrialRepository trialRepository) {

        trials = new ArrayList<>(); //Danh sach trials đang tồn tại
        trialMap = new HashMap<>(); //Tìm trial theo ID
        trialsWaiting = new ArrayDeque<>(); //FIFO, Trial được xác định là đang chờ đánh giá
        trialHistory = new ArrayDeque<>(); //LIFO, lịch sử Trial được thêm vào gần nhất

        this.trialRepository = trialRepository;
    }

    public boolean addTrial(Trial trial) throws InvalidDataException, DuplicateIdException {

        Validator.validateTrialId(trial.getIdTrial());

        Validator.validateName(trial.getNameTrial(), "Trial name");

        Validator.validatePositive(trial.getBatchSizeTrial(),"Trial batch size");


        if(trialMap.containsKey(trial.getIdTrial())) {
            // return false;
            throw new DuplicateIdException("Trial ID already exists: " + trial.getIdTrial());
        }
        trials.add(trial);
        trialMap.put(trial.getIdTrial(), trial);
        trialHistory.push(trial);
        return true;
    }

    public Trial findTrialById(String idTrial) {
        return trialMap.get(idTrial);
    }

    public List<Trial> findTrialsByName(String name) throws InvalidSearchDataException{

        Validator.validateSearchKeyword(name);

        List<Trial> result = new ArrayList<>();

        for(Trial trial : trials) {
            if (trial.getNameTrial().toLowerCase().contains(name.toLowerCase())) {
                result.add(trial);
            }
        }

        return result;
    }

    public boolean removeTrial(String idTrial) {
        Trial trial = trialMap.remove(idTrial);
        if (trial == null) {
            return false;
        }
        trials.remove(trial);
        trialsWaiting.remove(trial);
        return true;
    }

    public List<Trial> getAllTrials() {
        return new ArrayList<>(trials);
    }

    public void addWaitingTrial(Trial trial) {
        trialsWaiting.offer(trial);
    }

    public Trial getNextWaitingTrial() {
        return trialsWaiting.poll();
    }

    public Trial getLastAddedTrial() {
        if(trialHistory.isEmpty()) {
            return null;
        }
        return trialHistory.peek();
    }

    public List<Trial> sortTrials(
        IFSortingStrategy<Trial> sortingStrategy, 
        Comparator<Trial> comparator,
        SortDirection direction
    ) {
        List<Trial> sortedTrials = new ArrayList<>(trials);

        sortingStrategy.sort(sortedTrials, comparator, direction);
        return sortedTrials;
    }

    public void saveData() throws FileDataException {
        trialRepository.save(trials);
    }

    public void loadData(List<Formula> formulas) throws FileDataException {
        List<Trial> loadedTrials = trialRepository.load(formulas);

        trials.clear();
        trialMap.clear();
        trialsWaiting.clear();
        trialHistory.clear();

        for (Trial trial : loadedTrials) {
            trials.add(trial);
            trialMap.put(trial.getIdTrial(), trial);
            trialHistory.push(trial);
        }
    }

}
