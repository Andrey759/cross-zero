package Model.Game;

public class Line {
    
    private int index, count;
    private int[][] line;
    
    public Line(int arraySize) {
        line = new int[arraySize][2];
        index = 0;
        count = 0;
    }

    public Line() {

    }

    public void add(int x, int y) {
        int[] tmp = new int[2];
        for(int i = 0; i < index; i++)
            if(x < line[i][0] || y < line[index][1]) {
                tmp[0] = line[i][0];
                tmp[1] = line[i][1];
                line[i][0] = x;
                line[i][1] = y;
                x = tmp[0];
                y = tmp[1];
            }
        line[index][0] = x;
        line[index][1] = y;
        index++;
        count++;
    }
    
    public int getIndex() {
        return index;
    }
    
    public int getCount() {
        return count;
    }
    
    public void addCount() {
        count++;
    }
    
    public int getX(int index) {
        if(index <= this.index)
            return line[index][0];
        else
            return -1;
    }
    
    public int getY(int index) {
        if(index <= this.index)
            return line[index][1];
        else
            return -1;
    }
    
    public int getLastX(int offset) {
        if(offset <= this.index)
            return line[index - offset][0];
        else
            return -1;
    }
    
    public int getLastY(int offset) {
        if(offset <= this.index)
            return line[index - offset][1];
        else
            return -1;
    }
    
    public void free() {
        index = 0;
        count = 0;
    }
    
}
