import {TestSuite} from './test-suite.model';

export interface TestRun {
    id: number;
    status: string;
    suites: Array<TestSuite>;
}