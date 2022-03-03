package lab01.tdd;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SimpleCircularList implements CircularList {

    private static final int FIRST_INDEX = 0;
    private final List<Integer> list;
    private int currentIndex;

    public SimpleCircularList() {
        this.list = new LinkedList<>();
        this.currentIndex = FIRST_INDEX;
    }

    @Override
    public void add(int element) {
        this.list.add(element);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        if(this.list.isEmpty()){
            return Optional.empty();
        }else if(this.currentIndex == this.list.size()){
            this.currentIndex = FIRST_INDEX;
        }
        final int element = this.list.get(this.currentIndex);
        this.currentIndex++;
        return Optional.of(element);
    }

    @Override
    public Optional<Integer> previous() {
        if(this.list.isEmpty()){
            return Optional.empty();
        }else if(this.currentIndex == -1){
            this.currentIndex = this.list.size()-1;
        }
        final int element = this.list.get(this.currentIndex);
        this.currentIndex--;
        return Optional.of(element);
    }

    @Override
    public void reset() {
        this.currentIndex = FIRST_INDEX;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        if(this.list.isEmpty()){
            return Optional.empty();
        }else{
            for (int i = 0; i<this.list.size();i++){
                Optional<Integer> element = this.next();
                if(element.isPresent() && strategy.apply(element.get())){
                    return element;
                }
            }
        }
        return Optional.empty();
    }
}
