import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class zhou_p8 {
    private static int[] sortData;
    private static int[] searchData;
    private static JFrame frame;;
    private static JLabel[] leftLabels;
    private static JLabel[] rightLabels;
    // we have 10 labels on each side
    //sort int
    private static boolean selectionSortDone = false;
    private static JButton selectionSortButton;
    private static JButton binarySearchSortedButton;
    //add to binarySearchTree
    private static BinarySearchTree binarySearchTree = null;
    private static boolean binarySearchTreeDone = false;
    private static JButton binarySearchTreeButton;
    private static JButton binarySearchTreeSearchButton;
    //add to treeset
    private static TreeSet<Integer> treeSet = null;
    private static boolean treeSetDone = false;
    private static JButton treeSetButton;
    private static JButton treeSetSearchButton;
    //add to priority queue
    private static PriorityQueue<Integer> priorityQueue = null;
    private static boolean priorityQueueDone = false;
    private static JButton priorityQueueButton;
    private static JButton priorityQueueSearchButton;
    //add to hashset
    private static HashSet<Integer> hashSet = null;
    private static boolean hashSetDone = false;
    private static JButton hashSetButton;
    private static JButton hashSetSearchButton;
    //add to arraylist
    private static ArrayList<Integer> arrayList = null;
    private static boolean arrayListDone = false;
    private static JButton arrayListButton;
    private static JButton arrayListSearchButton;
    //add to sorted arraylist
    private static ArrayList<Integer> arrayListSorted = null;
    private static boolean arrayListSortedDone = false;
    private static JButton arrayListSortedButton;
    private static JButton arrayListBinarySearchButton;
    //add to array
    private static int[] sortedIntArray = null;
    private static boolean intArrayDone = false;
    private static JButton intArrayButton;
    private static JButton intArraySearchButton;
    //merge sort ints
    private static int[] mergeSortedArray = null;
    private static boolean mergeSortDone = false;
    private static JButton mergeSortButton;
    private static JButton mergeSortedBinarySearchButton;
    //merge2 sort ints
    private static int[] merge2SortedArray = null;
    private static boolean merge2SortDone = false;
    private static JButton merge2SortButton;
    private static JButton merge2SortedBinarySearchButton;
    //quick sort ints
    private static int[] quickSortedArray = null;
    private static boolean quickSortDone = false;
    private static JButton quickSortButton;
    private static JButton quickSortedBinarySearchButton;

    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("Please provide the two files sort and search files.");
            System.exit(0);
        }
        String sortFileName = args[0];
        String searchFileName = args[1];
        frame = new JFrame("Programming Assignment 8");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem readSortFile = new JMenuItem("Read sort file");
        JMenuItem readSearchFile = new JMenuItem("Read search file");
        JMenuItem exit = new JMenuItem("Exit");
        readSortFile.addActionListener(new MenuItemActionListener(sortFileName));
        readSearchFile.addActionListener(new MenuItemActionListener(searchFileName));
        exit.addActionListener(new MenuItemActionListener(null));
        fileMenu.add(readSortFile);
        fileMenu.add(readSearchFile);
        fileMenu.add(exit);
        menuBar.add(fileMenu);

        //left panel
        JPanel leftPanel = new JPanel();
        GridBagLayout leftGrid = new GridBagLayout();
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 8));
        leftPanel.setLayout(leftGrid);
        leftPanel.setPreferredSize(new Dimension(330, 350));
        leftPanel.setMinimumSize(new Dimension(330, 350));
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.fill = GridBagConstraints.NONE;
        gbcLeft.anchor = GridBagConstraints.LINE_START;
        selectionSortButton = new JButton("sort int");
        binarySearchTreeButton = new JButton("add to bst");
        treeSetButton = new JButton("add to treeset");
        priorityQueueButton = new JButton("add to priority queue");
        hashSetButton = new JButton("add to hashset");
        arrayListButton = new JButton("add to arraylist");
        arrayListSortedButton = new JButton("add to sorted arraylist");
        intArrayButton = new JButton("add to array");
        mergeSortButton = new JButton("merge sort ints");
        merge2SortButton = new JButton("merge2 sort ints");
        quickSortButton = new JButton("quick sort ints");
        JButton[] leftButtons = {selectionSortButton, binarySearchTreeButton, treeSetButton, priorityQueueButton, hashSetButton, arrayListButton, arrayListSortedButton, intArrayButton, mergeSortButton, merge2SortButton, quickSortButton};
        leftLabels = new JLabel[12];
        for (JButton Button : leftButtons) {
            Button.setEnabled(false);
        }
        selectionSortButton.addActionListener(new ButtonActionListener("Selection Sort"));
        binarySearchTreeButton.addActionListener(new ButtonActionListener("BST"));
        treeSetButton.addActionListener(new ButtonActionListener("TreeSet"));
        priorityQueueButton.addActionListener(new ButtonActionListener("PriorityQueue"));
        hashSetButton.addActionListener(new ButtonActionListener("HashSet"));
        arrayListButton.addActionListener(new ButtonActionListener("ArrayList"));
        arrayListSortedButton.addActionListener(new ButtonActionListener("ArrayListSorted"));
        intArrayButton.addActionListener(new ButtonActionListener("int[]"));
        mergeSortButton.addActionListener(new ButtonActionListener("MergeSort"));
        merge2SortButton.addActionListener(new ButtonActionListener("Merge2Sort"));
        quickSortButton.addActionListener(new ButtonActionListener("QuickSort"));
        for (int i = 0; i < leftButtons.length; i++) {
            leftLabels[i] = new JLabel("no result");
            leftLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            gbcLeft.gridx = 0;
            gbcLeft.gridy = i;
            leftPanel.add(leftButtons[i], gbcLeft);
            gbcLeft.gridx = 1;
            gbcLeft.fill = GridBagConstraints.HORIZONTAL;
            gbcLeft.weightx = 1.0;
            leftPanel.add(leftLabels[i], gbcLeft);
            gbcLeft.fill = GridBagConstraints.NONE;
            gbcLeft.weightx = 0.0;
        }
        
        //right panel
        JPanel rightPanel = new JPanel();
        GridBagLayout rightGrid = new GridBagLayout();
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 8));
        rightPanel.setLayout(rightGrid);
        rightPanel.setPreferredSize(new Dimension(330, 350));
        rightPanel.setMinimumSize(new Dimension(330, 350));
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.fill = GridBagConstraints.NONE;
        gbcRight.anchor = GridBagConstraints.LINE_START;
        binarySearchSortedButton = new JButton("search sorted ints");
        binarySearchTreeSearchButton = new JButton("search bst");
        treeSetSearchButton = new JButton("search treeset");
        priorityQueueSearchButton = new JButton("search priority queue");
        hashSetSearchButton = new JButton("search hashset");
        arrayListSearchButton = new JButton("search arraylist");
        arrayListBinarySearchButton = new JButton("search sorted arraylist");
        intArraySearchButton = new JButton("search array");
        mergeSortedBinarySearchButton = new JButton("search merge sorted ints");
        merge2SortedBinarySearchButton = new JButton("search merge2 sorted ints");
        quickSortedBinarySearchButton = new JButton("search quick sorted ints");
        JButton[] rightButtons = {binarySearchSortedButton, binarySearchTreeSearchButton, treeSetSearchButton, priorityQueueSearchButton, hashSetSearchButton, arrayListSearchButton, arrayListBinarySearchButton, intArraySearchButton, mergeSortedBinarySearchButton, merge2SortedBinarySearchButton, quickSortedBinarySearchButton};
        rightLabels = new JLabel[12];
        for (JButton Button : rightButtons) {
            Button.setEnabled(false);
        }
        binarySearchSortedButton.addActionListener(new ButtonActionListener("BinarySearchSorted"));
        binarySearchTreeSearchButton.addActionListener(new ButtonActionListener("BSTSearch"));
        treeSetSearchButton.addActionListener(new ButtonActionListener("TreeSetSearch"));
        priorityQueueSearchButton.addActionListener(new ButtonActionListener("PriorityQueueSearch"));
        hashSetSearchButton.addActionListener(new ButtonActionListener("HashSetSearch"));
        arrayListSearchButton.addActionListener(new ButtonActionListener("ArrayListSearch"));
        arrayListBinarySearchButton.addActionListener(new ButtonActionListener("ArrayListBinarySearch"));
        intArraySearchButton.addActionListener(new ButtonActionListener("int[]Search"));
        mergeSortedBinarySearchButton.addActionListener(new ButtonActionListener("MergeSortedBinarySearch"));
        merge2SortedBinarySearchButton.addActionListener(new ButtonActionListener("Merge2SortedBinarySearch"));
        quickSortedBinarySearchButton.addActionListener(new ButtonActionListener("QuickSortedBinarySearch"));
        for (int i = 0; i < rightButtons.length; i++) {
            rightLabels[i] = new JLabel("no result");
            rightLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            gbcRight.gridx = 0;
            gbcRight.gridy = i;
            rightPanel.add(rightButtons[i], gbcRight);
            gbcRight.gridx = 1;
            gbcRight.fill = GridBagConstraints.HORIZONTAL;
            gbcRight.weightx = 1.0;
            rightPanel.add(rightLabels[i], gbcRight);
            gbcRight.fill = GridBagConstraints.NONE;
            gbcRight.weightx = 0.0;
        }
        JPanel mainButtonPanel = new JPanel();
        BoxLayout mainGrid = new BoxLayout(mainButtonPanel, BoxLayout.X_AXIS);
        mainButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));
        mainButtonPanel.setLayout(mainGrid);
        mainButtonPanel.add(leftPanel);
        mainButtonPanel.add(rightPanel);
        JPanel mainPanel = new JPanel();
        BorderLayout mainLayout = new BorderLayout();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 8));
        mainPanel.setLayout(mainLayout);
        mainPanel.add(menuBar, BorderLayout.NORTH);
        mainPanel.add(mainButtonPanel, BorderLayout.CENTER);
        frame.setContentPane(mainPanel);
        frame.validate();
        frame.setVisible(true);
    }

    //zhou_p8$MenuItemActionListener.class
    static class MenuItemActionListener implements ActionListener {
        private String fileName;
        private boolean isExit;
        
        public MenuItemActionListener(String fileName) {
            this.fileName = fileName;
            this.isExit = (fileName == null);
        }
        
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("Exit") || isExit) {
                System.exit(0);
            } else if (cmd.equals("Read sort file")) {
                readSortFile(fileName);
            } else if (cmd.equals("Read search file")) {
                readSearchFile(fileName);
            }
        }
    }
    
    //zhou_p8$ButtonActionListener.class
    static class ButtonActionListener implements ActionListener {
        private String buttonType;
        
        public ButtonActionListener(String buttonType) {
            this.buttonType = buttonType;
        }
        
        public void actionPerformed(ActionEvent e) {
            if (buttonType.equals("Selection Sort")) {
                performSelectionSort();
            } else if (buttonType.equals("BST")) {
                performAddToBST();
            } else if (buttonType.equals("TreeSet")) {
                performAddToTreeSet();
            } else if (buttonType.equals("PriorityQueue")) {
                performAddToPriorityQueue();
            } else if (buttonType.equals("HashSet")) {
                performAddToHashSet();
            } else if (buttonType.equals("ArrayList")) {
                performAddToArrayList();
            } else if (buttonType.equals("ArrayListSorted")) {
                performAddToArrayListSorted();
            } else if (buttonType.equals("int[]")) {
                performAddToIntArray();
            } else if (buttonType.equals("MergeSort")) {
                performMergeSort();
            } else if (buttonType.equals("Merge2Sort")) {
                performMerge2Sort();
            } else if (buttonType.equals("QuickSort")) {
                performQuickSort();
            } else if (buttonType.equals("BinarySearchSorted")) {
                performBinarySearchSorted();
            } else if (buttonType.equals("BSTSearch")) {
                performBSTSearch();
            } else if (buttonType.equals("TreeSetSearch")) {
                performTreeSetSearch();
            } else if (buttonType.equals("PriorityQueueSearch")) {
                performPriorityQueueSearch();
            } else if (buttonType.equals("HashSetSearch")) {
                performHashSetSearch();
            } else if (buttonType.equals("ArrayListSearch")) {
                performArrayListSearch();
            } else if (buttonType.equals("ArrayListBinarySearch")) {
                performArrayListBinarySearch();
            } else if (buttonType.equals("int[]Search")) {
                performIntArraySearch();
            } else if (buttonType.equals("MergeSortedBinarySearch")) {
                performMergeSortedBinarySearch();
            } else if (buttonType.equals("Merge2SortedBinarySearch")) {
                performMerge2SortedBinarySearch();
            } else if (buttonType.equals("QuickSortedBinarySearch")) {
                performQuickSortedBinarySearch();
            }
        }
    }
    
    private static void readSortFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            ArrayList<Integer> list = new ArrayList<>();
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
            scanner.close();
            sortData = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                sortData[i] = list.get(i);
            }
            selectionSortButton.setEnabled(true);
            binarySearchTreeButton.setEnabled(true);
            treeSetButton.setEnabled(true);
            priorityQueueButton.setEnabled(true);
            hashSetButton.setEnabled(true);
            arrayListButton.setEnabled(true);
            arrayListSortedButton.setEnabled(true);
            intArrayButton.setEnabled(true);
            mergeSortButton.setEnabled(true);
            merge2SortButton.setEnabled(true);
            quickSortButton.setEnabled(true);
            enableSearchButtonsIfReady();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + fileName);
        }
    }
    
    private static void readSearchFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            ArrayList<Integer> list = new ArrayList<>();
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
            scanner.close();
            searchData = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                searchData[i] = list.get(i);
            }
            enableSearchButtonsIfReady();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + fileName);
        }
    }
    
    private static void enableSearchButtonsIfReady() {
        if (searchData == null) return;
        if (selectionSortDone) {
            binarySearchSortedButton.setEnabled(true);
        }
        if (binarySearchTreeDone) {
            binarySearchTreeSearchButton.setEnabled(true);
        }
        if (treeSetDone) {
            treeSetSearchButton.setEnabled(true);
        }
        if (priorityQueueDone) {
            priorityQueueSearchButton.setEnabled(true);
        }
        if (hashSetDone) {
            hashSetSearchButton.setEnabled(true);
        }
        if (arrayListDone) {
            arrayListSearchButton.setEnabled(true);
        }
        if (arrayListSortedDone) {
            arrayListBinarySearchButton.setEnabled(true);
        }
        if (intArrayDone) {
            intArraySearchButton.setEnabled(true);
        }
        if (mergeSortDone) {
            mergeSortedBinarySearchButton.setEnabled(true);
        }
        if (merge2SortDone) {
            merge2SortedBinarySearchButton.setEnabled(true);
        }
        if (quickSortDone) {
            quickSortedBinarySearchButton.setEnabled(true);
        }
    }

    // preform the various add/sort operations
    // i coded the algorithms after I made sure the buttons worked so algorithms were added at the end
    private static void performSelectionSort() {
        if (sortData == null){
            return;
        }
        int[] dataCopy = Arrays.copyOf(sortData, sortData.length);
        long startTime = System.nanoTime();
        selectionSort(dataCopy);
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[0].setText((int)timeMs + " ms");
        selectionSortDone = true;
        sortedIntArray = dataCopy;
        if (searchData != null) {
            binarySearchSortedButton.setEnabled(true);
        }
    }
    
    private static void performAddToBST() {
        if (sortData == null){
            return;
        }
        binarySearchTree = new BinarySearchTree();
        long startTime = System.nanoTime();
        for (int value : sortData) {
            binarySearchTree.insert(value);
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[1].setText((int)timeMs + " ms");
        binarySearchTreeDone = true;
        if (searchData != null) {
            binarySearchTreeSearchButton.setEnabled(true);
        }
    }
    
    private static void performAddToTreeSet() {
        if (sortData == null){
            return;
        }
        treeSet = new TreeSet<>();
        long startTime = System.nanoTime();
        for (int value : sortData) {
            treeSet.add(value);
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[2].setText((int)timeMs + " ms");
        treeSetDone = true;
        if (searchData != null) {
            treeSetSearchButton.setEnabled(true);
        }
    }
    
    private static void performAddToPriorityQueue() {
        if (sortData == null){
            return;
        }
        priorityQueue = new PriorityQueue<>();
        long startTime = System.nanoTime();
        for (int value : sortData) {
            priorityQueue.add(value);
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[3].setText((int)timeMs + " ms");
        priorityQueueDone = true;
        if (searchData != null) {
            priorityQueueSearchButton.setEnabled(true);
        }
    }
    
    private static void performAddToHashSet() {
        if (sortData == null){
            return;
        }
        hashSet = new HashSet<>();
        long startTime = System.nanoTime();
        for (int value : sortData) {
            hashSet.add(value);
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[4].setText((int)timeMs + " ms");
        hashSetDone = true;
        if (searchData != null) {
            hashSetSearchButton.setEnabled(true);
        }
    }
    
    private static void performAddToArrayList() {
        if (sortData == null){
            return;
        }
        arrayList = new ArrayList<>();
        long startTime = System.nanoTime();
        for (int value : sortData) {
            arrayList.add(value);
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[5].setText((int)timeMs + " ms");
        arrayListDone = true;
        if (searchData != null) {
            arrayListSearchButton.setEnabled(true);
        }
    }
    
    private static void performAddToArrayListSorted() {
        if (sortData == null){
            return;
        }
        arrayListSorted = new ArrayList<>();
        long startTime = System.nanoTime();
        for (int value : sortData) {
            arrayListSorted.add(value);
        }
        Collections.sort(arrayListSorted);
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[6].setText((int)timeMs + " ms");
        arrayListSortedDone = true;
        if (searchData != null) {
            arrayListBinarySearchButton.setEnabled(true);
        }
    }
    
    private static void performAddToIntArray() {
        if (sortData == null){
            return;
        }
        sortedIntArray = Arrays.copyOf(sortData, sortData.length);
        long startTime = System.nanoTime();
        Arrays.sort(sortedIntArray);
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[7].setText((int)timeMs + " ms");
        intArrayDone = true;
        if (searchData != null) {
            intArraySearchButton.setEnabled(true);
        }
    }
    
    private static void performMergeSort() {
        if (sortData == null){
            return;
        }
        mergeSortedArray = Arrays.copyOf(sortData, sortData.length);
        long startTime = System.nanoTime();
        mergeSort(mergeSortedArray, 0, mergeSortedArray.length - 1);
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[8].setText((int)timeMs + " ms");
        mergeSortDone = true;
        if (searchData != null) {
            mergeSortedBinarySearchButton.setEnabled(true);
        }
    }
    
    private static void performMerge2Sort() {
        if (sortData == null){
            return;
        }
        merge2SortedArray = Arrays.copyOf(sortData, sortData.length);
        long startTime = System.nanoTime();
        mergeSort2(merge2SortedArray, 0, merge2SortedArray.length - 1);
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[9].setText((int)timeMs + " ms");
        merge2SortDone = true;
        if (searchData != null) {
            merge2SortedBinarySearchButton.setEnabled(true);
        }
    }
    
    private static void performQuickSort() {
        if (sortData == null){
            return;
        }
        quickSortedArray = Arrays.copyOf(sortData, sortData.length);
        long startTime = System.nanoTime();
        quickSort(quickSortedArray, 0, quickSortedArray.length - 1);
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        leftLabels[10].setText((int)timeMs + " ms");
        quickSortDone = true;
        if (searchData != null) {
            quickSortedBinarySearchButton.setEnabled(true);
        }
    }
    
    // Search operations
    private static void performBinarySearchSorted() {
        if (searchData == null || sortedIntArray == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (binarySearch(sortedIntArray, target) >= 0) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[0].setText(found + " / " + (int)timeMs + " ms");
    }
    
    private static void performBSTSearch() {
        if (searchData == null || binarySearchTree == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (binarySearchTree.search(target)) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[1].setText(found + " / " + (int)timeMs + " ms");
    }
    
    private static void performTreeSetSearch() {
        if (searchData == null || treeSet == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (treeSet.contains(target)) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[2].setText(found + " / " + (int)timeMs + " ms");
    }
    
    private static void performPriorityQueueSearch() {
        if (searchData == null || priorityQueue == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (priorityQueue.contains(target)) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[3].setText(found + " / " + (int)timeMs + " ms");
    }
    
    private static void performHashSetSearch() {
        if (searchData == null || hashSet == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (hashSet.contains(target)) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[4].setText(found + " / " + (int)timeMs + " ms");
    }
    
    private static void performArrayListSearch() {
        if (searchData == null || arrayList == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (arrayList.contains(target)) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[5].setText(found + " / " + (int)timeMs + " ms");
    }
    
    private static void performArrayListBinarySearch() {
        if (searchData == null || arrayListSorted == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (Collections.binarySearch(arrayListSorted, target) >= 0) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[6].setText(found + " / " + (int)timeMs + " ms");
    }
    
    private static void performIntArraySearch() {
        if (searchData == null || sortedIntArray == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (Arrays.binarySearch(sortedIntArray, target) >= 0) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[7].setText(found + " / " + (int)timeMs + " ms");
    }
    
    private static void performMergeSortedBinarySearch() {
        if (searchData == null || mergeSortedArray == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (binarySearch(mergeSortedArray, target) >= 0) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[8].setText(found + " / " + (int)timeMs + " ms");
    }
    
    private static void performMerge2SortedBinarySearch() {
        if (searchData == null || merge2SortedArray == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (binarySearch(merge2SortedArray, target) >= 0) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[9].setText(found + " / " + (int)timeMs + " ms");
    }
    
    private static void performQuickSortedBinarySearch() {
        if (searchData == null || quickSortedArray == null){
            return;
        }
        int found = 0;
        long startTime = System.nanoTime();
        for (int target : searchData) {
            if (binarySearch(quickSortedArray, target) >= 0) {
                found++;
            }
        }
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1000000.0;
        rightLabels[10].setText(found + " / " + (int)timeMs + " ms");
    }
    
    //selection sort sorting algorithm
    private static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    //merge sort sorting algorithm
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    //idk why i have a second one but sure
    private static void mergeSort2(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort2(arr, left, mid);
            mergeSort2(arr, mid + 1, right);
            merge2(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int firstPointer = mid - left + 1;
        int secondPointer = right - mid;
        
        int[] leftArr = new int[firstPointer];
        int[] rightArr = new int[secondPointer];
        
        for (int i = 0; i < firstPointer; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < secondPointer; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        while (i < firstPointer && j < secondPointer) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        while (i < firstPointer) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < secondPointer) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    private static void merge2(int[] arr, int left, int mid, int right) {
        int firstPointer = mid - left + 1;
        int secondPointer = right - mid;
        
        int[] leftArr = new int[firstPointer];
        int[] rightArr = new int[secondPointer];
        
        for (int i = 0; i < firstPointer; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < secondPointer; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        while (i < firstPointer && j < secondPointer) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        while (i < firstPointer) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < secondPointer) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    //quicksort sorting algorithm
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    //binary search algorithm
    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            }
            
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
}

