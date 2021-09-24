public class ShellSort{
    int[] arr = {9,4,2,4,5,6,7,7,1,2,3,12,3};
    public void shellSort(){
        for(int gap = arr.length/2; gap >= 1; gap /=2){
            for(int i =gap; i < arr.length; i++){
                int j = i;
                while(j >= gap){
                    if(arr[j] < arr[j-gap]){
                        this.swap(j, j-gap);
                        j-= gap;
                    }
                    else break;
                }
            }
        }
    }

    private void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        ShellSort sort = new ShellSort();
        sort.shellSort();
        for(int i: sort.arr) System.out.println(i);
    }
}