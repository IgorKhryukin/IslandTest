package factory;

import entity.map.Cell;
import entity.map.GameMap;

import java.util.List;

public class GameMapCreator {

    private final Factory entityFactory;


    public GameMapCreator(Factory entityFactory) {
        this.entityFactory = entityFactory;
    }

    public GameMap createRandomGameMap (int rows, int cols){
        GameMap gameMap = new GameMap(rows, cols);
        Cell[][] cells = gameMap.getGameMap();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = entityFactory.createRandomCell();
            }
        }
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];
                List<Cell> nextCell = cell.getNextCell();
                if (row > 0) nextCell.add(cells[row - 1][col]);
                if (col > 0) nextCell.add(cells[row][col - 1]);
                if (row < rows - 1) nextCell.add(cells[row + 1][col]);
                if (col < cols - 1) nextCell.add(cells[row][col + 1]);
            }
        }
        return gameMap;
    }

}
