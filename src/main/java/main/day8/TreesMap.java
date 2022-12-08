package main.day8;

import java.util.List;

public class TreesMap {
    private final List<List<Integer>> map;
    private int visibleTrees;

    public TreesMap(List<List<Integer>> treesMap) {
        this.map = treesMap;
        this.visibleTrees = treesMap.size() * 2 + (treesMap.get(0).size()-2)*2;
    }

    public int getVisibleTrees() {
        for(var i=1;i<map.size() -1 ;i++) {
            for (var j = 1; j < map.get(0).size() -1; j++) {
                Integer treeHeight = map.get(i).get(j);
                if(isVisible(i, j, treeHeight)){
                    visibleTrees = visibleTrees + 1;
                }

            }
        }

        return visibleTrees;
    }
    private boolean isVisible(int i, int j, Integer treeHeight) {
        boolean topVisible = isTopVisible(i, j, treeHeight);
        boolean bottomVisible = isBottomVisible(i, j, treeHeight);
        boolean leftVisible = isLeftVisible(i, j, treeHeight);
        boolean rightVisible = isRightVisible(i, j, treeHeight);
        return topVisible || bottomVisible || leftVisible || rightVisible;
    }
    private boolean isBottomVisible(int i, int j, Integer treeHeight) {
        for(var k = i+1; k < map.size() ; k++) {
            if (isHiddenBy(k, j, treeHeight)) return false;
        }
        return true;
    }

    private boolean isRightVisible(int i, int j, Integer treeHeight) {
        for(var k = j+1; k < map.get(0).size() ; k++) {
            if (isHiddenBy(i, k, treeHeight)) return false;
        }
        return true;
    }

    private  boolean isTopVisible(int i, int j,Integer treeHeight) {
        for(var k = i-1; k >= 0 ; k--) {
            if (isHiddenBy(k, j, treeHeight)) return false;
        }
        return true;
    }

    private  boolean isLeftVisible(int i, int j,Integer treeHeight) {
        for(var k = j-1; k >= 0 ; k--) {
            if (isHiddenBy(i, k ,treeHeight)) return false;
        }
        return true;
    }

    private boolean isHiddenBy(int row, int column, Integer treeHeight) {
        return map.get(row).get(column) >= treeHeight;
    }

    public int getMaxScenicScore() {
        int maxScore = 0;
        for(var i=1;i<map.size() -1 ;i++) {
            for (var j = 1; j < map.get(0).size() -1; j++) {
                Integer treeHeight = map.get(i).get(j);
                int score = getScenicScore(i, j, treeHeight);
                if(score > maxScore){
                    maxScore = score;
                }

            }
        }

        return maxScore;
    }

    private int getScenicScore(int i, int j, Integer treeHeight) {
        int topScore = topScore(i, j, treeHeight);
        int bottomScore = bottomScore(i, j, treeHeight);
        int leftScore = leftScore(i, j, treeHeight);
        int rightScore = rightScore(i, j, treeHeight);
        return topScore * bottomScore * leftScore * rightScore;
    }
    private int bottomScore(int i, int j, Integer treeHeight) {
        int score = 0;
        for(var k = i+1; k < map.size() ; k++) {
            if (!isHiddenBy(k, j, treeHeight)) {
                score ++;
            }
            else {
                return score + 1;
            }
        }
        return score;
    }

    private int rightScore(int i, int j, Integer treeHeight) {
        int score = 0;
        for(var k = j+1; k < map.get(0).size() ; k++) {
            if (!isHiddenBy(i, k, treeHeight)) {
                score = score + 1;
            }
            else {
                return score+1;
            }
        }
        return score;
    }

    private  int topScore(int i, int j,Integer treeHeight) {
        int score = 0;
        for(var k = i-1; k >= 0 ; k--) {
            if (!isHiddenBy(k, j, treeHeight)) score ++;
            else {
                return score+1;
            }        }
        return score;
    }

    private  int leftScore(int i, int j,Integer treeHeight) {
        int score = 0;
        for(var k = j-1; k >= 0 ; k--) {
            if (!isHiddenBy(i, k, treeHeight)) score ++;
            else {
                return score+1;
            }
        }
        return score;
    }

}
