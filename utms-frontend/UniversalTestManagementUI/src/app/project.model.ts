import {TestRun} from './test-run.model';

export interface Project {
    id: number;
    name: string;
    description: string;
    runs: Array<TestRun>;
}