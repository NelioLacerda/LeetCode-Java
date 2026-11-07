package challenges;

import java.util.PriorityQueue;

public class ProcessTasksUsingServers implements Challenge{

    public int[] assignTasks(int[] servers, int[] tasks) {
        int[] result = new int[tasks.length];
        PriorityQueue<int[]> sv_free = new PriorityQueue<>((a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        PriorityQueue<int[]> sv_busy = new PriorityQueue<>((a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : a[1] != b[1] ? Integer.compare(a[1], b[1]) : Integer.compare(a[2], b[2]));

        for (int i = 0; i < servers.length; i++)
            sv_free.add(new int[]{servers[i], i});

        int time = 0;
        for (int i = 0; i < tasks.length; i++) {
            time = Math.max(time, i);
            if (sv_free.isEmpty()) {
                int next = sv_busy.peek()[0];
                time = Math.max(time, next);
            }
            while (!sv_busy.isEmpty() && sv_busy.peek()[0] <= time) {
                int[] sv = sv_busy.poll();
                sv_free.add(new int[]{sv[1], sv[2]});
            }
            int[] sv = sv_free.poll();
            sv_busy.add(new int[]{time + tasks[i], sv[0], sv[1]});
            result[i] = sv[1];
        }
        return result;
    }

    @Override
    public void getSolutions() {

    }
}
