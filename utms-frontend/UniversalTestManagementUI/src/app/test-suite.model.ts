import {TestCase} from './test-case.model';

export interface TestSuite {
    name: string;
    status: string;
    tests: Array<TestCase>;
}